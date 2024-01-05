package com.example.myproject.DAO;

import com.example.myproject.DTO.MenuDTO;
import com.example.myproject.DTO.SearchDTO;
import com.example.myproject.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MenuDao {

    public List<MenuDTO> menu(SearchDTO searchDTO);

}
