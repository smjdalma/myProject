package com.example.myproject.mapper;

import com.example.myproject.DTO.PagingUserDTO;
import com.example.myproject.DTO.SearchDTO;
import com.example.myproject.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<UserDTO> users(SearchDTO searchDTO);

    public PagingUserDTO getCount();

    public PagingUserDTO getCount(String searchField, String searchString);

    public int insert(UserDTO userDTO);

    public int insert1000(List<UserDTO> list);

    public int update(UserDTO userDTO);

    public int delete(int num);

}
