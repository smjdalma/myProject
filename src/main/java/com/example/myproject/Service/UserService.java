package com.example.myproject.Service;

import com.example.myproject.DAO.UserDao;
import com.example.myproject.DTO.PagingUserDTO;
import com.example.myproject.DTO.SearchDTO;
import com.example.myproject.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    public final UserDao userDao;

    public List<UserDTO> users(int page, int rows) {
        SearchDTO searchDTO = new SearchDTO(page, rows);
        List<UserDTO> list = userDao.users(searchDTO);
        return list != null ? list : Collections.emptyList();
    }

    public List<UserDTO> users(int page, int rows, String searchField, String searchString) {
        SearchDTO searchDTO = new SearchDTO(page, rows, searchField, searchString);
        List<UserDTO> list = userDao.users(searchDTO);
        return list != null ? list : Collections.emptyList();
    }

    public PagingUserDTO getCount() {
        return userDao.getCount();
    }

    public PagingUserDTO getCount(String searchField, String searchString) {
        return userDao.getCount(searchField, searchString);
    }

    public int insert(UserDTO userDTO) {
        return userDao.insert(userDTO);
    }

    public int update(UserDTO userDTO) {
        return userDao.update(userDTO);
    }

    public int delete(int num) {
        return userDao.delete(num);
    }
}
