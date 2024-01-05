package com.example.myproject.DAO.impl;

import com.example.myproject.DAO.MenuDao;
import com.example.myproject.DTO.MenuDTO;
import com.example.myproject.DTO.SearchDTO;
import com.example.myproject.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuDaoImpl implements MenuDao {

    public final MenuMapper menuMapper;

    @Override
    public List<MenuDTO> menu(SearchDTO searchDTO) {
        return menuMapper.menu(searchDTO);
    }
}
