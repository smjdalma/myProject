package com.example.myproject.Service;


import com.example.myproject.DAO.impl.MenuDaoImpl;
import com.example.myproject.DTO.MenuDTO;
import com.example.myproject.DTO.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuService {
    public List<MenuDTO> menu(int page, int rows, String searchField, String searchString);
}
