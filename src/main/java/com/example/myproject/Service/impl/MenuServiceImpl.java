package com.example.myproject.Service.impl;


import com.example.myproject.DAO.impl.MenuDaoImpl;
import com.example.myproject.DTO.MenuDTO;
import com.example.myproject.DTO.SearchDTO;
import com.example.myproject.Service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuDaoImpl menuDaoImpl;

    public List<MenuDTO> menu() {
        return menuDaoImpl.menu(null);
    }

    public List<MenuDTO> menu(int page, int rows, String searchField, String searchString) {
        SearchDTO searchDTO = new SearchDTO(page, rows, searchField, searchString);
        return menuDaoImpl.menu(searchDTO);
    }

}
