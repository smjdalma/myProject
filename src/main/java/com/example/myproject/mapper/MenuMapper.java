package com.example.myproject.mapper;

import com.example.myproject.DTO.MenuDTO;
import com.example.myproject.DTO.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    public List<MenuDTO> menu(SearchDTO searchDTO);
}
