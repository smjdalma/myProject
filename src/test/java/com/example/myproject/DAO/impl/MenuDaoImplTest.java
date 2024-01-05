package com.example.myproject.DAO.impl;

import com.example.myproject.DTO.MenuDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MenuDaoImplTest {

    @Autowired
    private MenuDaoImpl menuDaoImpl;

    @Test
    public void testMenu() {
        List<MenuDTO> list = menuDaoImpl.menu(null);
        log.info("list : {}", list);
    }


}