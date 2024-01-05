package com.example.myproject.Controller;

import com.example.myproject.DTO.PagingUserDTO;
import com.example.myproject.DTO.UserDTO;
import com.example.myproject.Response.BasicResponse;
import com.example.myproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/home")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    public final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<BasicResponse<PagingUserDTO>> users(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "") int rows)
            throws Exception {

        log.info("page : {}", page);
        log.info("rows : {}", rows);

        List<UserDTO> list = userService.users(page, rows);
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        PagingUserDTO total = userService.getCount();
        PagingUserDTO pagingUserDTO = new PagingUserDTO();
//        pagingUserDTO.setRecords(59999);
        pagingUserDTO.setTotal(total.getTotal());
        pagingUserDTO.setPage(page);
        pagingUserDTO.setList(list);

        BasicResponse<PagingUserDTO> basicResponse = new BasicResponse<>();
        basicResponse.setCode(HttpStatus.OK.value());
        basicResponse.setHttpStatus(HttpStatus.OK);
        basicResponse.setMessage("전체 목록 조회 완료");
        basicResponse.setCount(list.size());
        basicResponse.setResult(pagingUserDTO);
        return ResponseEntity.ok(basicResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<BasicResponse<PagingUserDTO>> user(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "") int rows,
            @RequestParam(value = "searchField") String searchField,
            @RequestParam(value = "searchString") String searchString
    ) throws Exception {

        log.info("got a search request");
        log.info("page : {}", page);
        log.info("rows : {}", rows);
        log.info("searchField : {}", searchField);
        log.info("searchString : {}", searchString);

        List<UserDTO> list = userService.users(page, rows, searchField, searchString);
        PagingUserDTO total = userService.getCount(searchField, searchString);
        PagingUserDTO pagingUserDTO = new PagingUserDTO();

        log.info("list : {}", list);
        pagingUserDTO.setTotal(total.getTotal());
        pagingUserDTO.setPage(page);
        pagingUserDTO.setList(list);

        BasicResponse<PagingUserDTO> basicResponse = new BasicResponse<>();
        basicResponse.setCode(HttpStatus.OK.value());
        basicResponse.setHttpStatus(HttpStatus.OK);
        basicResponse.setMessage("검색 목록 조회 완료");
        basicResponse.setCount(list.size());
        basicResponse.setResult(pagingUserDTO);
        return ResponseEntity.ok(basicResponse);
    }

    @PostMapping("/insert")
    public ResponseEntity<BasicResponse<UserDTO>> insert(
            @Valid @RequestBody UserDTO userDTO
    ) {
        log.info("got a search request"); // 도달안함
        log.info("userDTO : {}", userDTO);
        int count = userService.insert(userDTO);
        BasicResponse<UserDTO> basicResponse = new BasicResponse<>();
        if (count == 1) {
            basicResponse.setCode(HttpStatus.OK.value());
            basicResponse.setHttpStatus(HttpStatus.OK);
            basicResponse.setMessage("사용자 추가 성공");
            basicResponse.setCount(1);
            basicResponse.setResult(userDTO);
        } else {
            basicResponse.setCode(HttpStatus.BAD_REQUEST.value());
            basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            basicResponse.setMessage("사용자 추가 실패");
            basicResponse.setCount(0);
            basicResponse.setResult(null);
        }
        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody UserDTO userDTO) {
        log.info("got a put request");

        BasicResponse<UserDTO> basicResponse = new BasicResponse<>();
        int count = userService.update(userDTO);
        if (count == 1) {
            basicResponse.setCode(HttpStatus.OK.value());
            basicResponse.setHttpStatus(HttpStatus.OK);
            basicResponse.setMessage("사용자 수정 성공");
            basicResponse.setCount(1);
            basicResponse.setResult(userDTO);
        } else {
            basicResponse.setCode(HttpStatus.BAD_REQUEST.value());
            basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            basicResponse.setMessage("사용자 수정 실패");
            basicResponse.setCount(0);
            basicResponse.setResult(userDTO);
        }
        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @DeleteMapping("/delete/{num}")
    public ResponseEntity<BasicResponse<UserDTO>> delete(@PathVariable Integer num) {
        // 삭제할 유저 검색
        int page = 1;
        int rows = 1;
        String selection = "num";
        String input = String.valueOf(num);
        List<UserDTO> list = userService.users(page, rows, selection, input);
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        // 삭제 후 처리
        BasicResponse<UserDTO> basicResponse = new BasicResponse<>();
        int count = userService.delete(num);
        if (count == 1) {
            basicResponse.setCode(HttpStatus.OK.value());
            basicResponse.setHttpStatus(HttpStatus.OK);
            basicResponse.setMessage("사용자 삭제 성공");
            basicResponse.setCount(1);
            basicResponse.setResult(list.get(0));
        } else {
            basicResponse.setCode(HttpStatus.BAD_REQUEST.value());
            basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            basicResponse.setMessage("사용자 삭제 실패");
            basicResponse.setCount(0);
            basicResponse.setResult(null);
        }
        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }
}