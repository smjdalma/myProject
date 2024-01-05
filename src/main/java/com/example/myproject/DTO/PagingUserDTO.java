package com.example.myproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingUserDTO {
    private int page;
    private int records;
    private int total;
    private List<UserDTO> list;
}

