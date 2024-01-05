package com.example.myproject;

import com.example.myproject.DTO.UserDTO;
import com.example.myproject.Response.BasicResponse;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<BasicResponse<UserDTO>> handleNoSuchElementException() {

        BasicResponse<UserDTO> basicResponse = new BasicResponse<>();
        basicResponse.setCode(HttpStatus.NOT_FOUND.value());
        basicResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        basicResponse.setMessage("해당하는 요소가 없습니다.");
        basicResponse.setCount(0);
        basicResponse.setResult(null);
        return new ResponseEntity<>(basicResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<BasicResponse<UserDTO>> handleDuplicateKeyException() {
        BasicResponse<UserDTO> basicResponse = new BasicResponse<>();
        basicResponse.setCode(HttpStatus.BAD_REQUEST.value());
        basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        basicResponse.setMessage("실패 : 개체 무결성 위반입니다.");
        basicResponse.setCount(0);
        basicResponse.setResult(null);
        return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BasicResponse<UserDTO>> handleMethodArgumentNotValidException() {
        BasicResponse<UserDTO> basicResponse = new BasicResponse<>();
        basicResponse.setCode(HttpStatus.BAD_REQUEST.value());
        basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        basicResponse.setMessage("실패 : 유효성 위반입니다.");
        basicResponse.setCount(0);
        basicResponse.setResult(null);
        return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
    }



}
