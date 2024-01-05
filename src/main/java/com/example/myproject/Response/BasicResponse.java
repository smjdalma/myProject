package com.example.myproject.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse<T> {
    private int code;
    private HttpStatus httpStatus;
    private String message;
    private Integer count;
    private T result;
}
