package com.example.myproject.DAO;

import com.example.myproject.DTO.PagingUserDTO;
import com.example.myproject.DTO.SearchDTO;
import com.example.myproject.DTO.UserDTO;
import com.example.myproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserDao {

    public final UserMapper userMapper;

    public List<UserDTO> users(SearchDTO searchDTO) {
        return userMapper.users(searchDTO);
    }

    public PagingUserDTO getCount() {
        return userMapper.getCount();
    }

    public PagingUserDTO getCount(String searchField, String searchString) {
        return userMapper.getCount(searchField, searchString);
    }

    public int insert(UserDTO userDTO) {
        return userMapper.insert(userDTO);
    }

    public int insert1000(List<UserDTO> list) { return userMapper.insert1000(list); }

    public int update(UserDTO userDTO) {
        return userMapper.update(userDTO);
    }

    public int delete(int num) {
        return userMapper.delete(num);
    }

}
