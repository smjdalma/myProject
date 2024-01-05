package com.example.myproject.DAO.impl;

import com.example.myproject.DAO.UserDao;
import com.example.myproject.DTO.PagingUserDTO;
import com.example.myproject.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
class UserDaoTest {

    private final UserDao userDao;



    @Test
    public void testUpdate() {
//        int result = userDao.update(new UserDTO(1, "jang", "jeju"));
//        assertEquals(1, result);
    }

    @Test
    public void testInsert1000() {

        int current_num = 1; // 마지막 번호
        final int INSERT_COUNT = 1000; // 넣을 횟수
        String[] adds = {"서울", "부산", "인천", "광주", "대전", "대구"}; // 주소 로테이션

        List<UserDTO> list = new ArrayList<>();
        UserDTO userDTO = null;
        for(int i = 0; i < INSERT_COUNT; i++) {
            userDTO = new UserDTO();
//            userDTO.setNum((i + 1));
            userDTO.setName("홍길동" + (i + 1));
            userDTO.setAdd(adds[i % adds.length]);
            list.add(userDTO);
        }
        int result_count = userDao.insert1000(list);
        assertEquals(1000, result_count);
    }

    @Test
    public void testCount() {
        PagingUserDTO pagingUserDTO = userDao.getCount();
        log.info("jsonObj : {}", pagingUserDTO);
    }

    @Test
    public void testUser() {
        int page = 55;
        int rows = 10;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", rows);
//        List<UserDTO> list = userDao.users(map);
//        log.info("list : {}", list);
    }


}