package com.example.myproject.Controller;

import com.example.myproject.DTO.MenuDTO;
import com.example.myproject.Service.impl.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/menu")
public class MenuController {

    private final MenuServiceImpl menuServiceImpl;

    @GetMapping("")
    public String jqGrid(Model model) {
        List<MenuDTO> list = menuServiceImpl.menu();
        model.addAttribute("list", list);
        return "menu";
    }

    @ResponseBody
    @GetMapping("/menulist")
    public List<MenuDTO> menuList() {
        // ResponseEntity 로 받아올 수 있도록 수정
       return menuServiceImpl.menu();
    }

    @GetMapping("/page")
    public String page(@RequestParam(value = "id") String id) {
        return "page/page" + id;
    }

    /*
    @GetMapping("/menu/grid")
    @ResponseBody
    public List<MenuDTO> grid(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "") int rows,
            @RequestParam(value = "searchField", required = false, defaultValue = "menuname") String searchField,
            @RequestParam(value = "searchString", required = false) String searchString
    ) throws Exception {
        return menuServiceImpl.menu(page, rows, searchField, searchString);
    }
     */

}
