package com.example.myproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    int page;
    int rows;
    String searchField;
    String searchString;

    public SearchDTO(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }
}
