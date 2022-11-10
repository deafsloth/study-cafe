package com.jdong.studycafe.service;

import com.jdong.studycafe.menus.dto.MenuDTO;
import com.jdong.studycafe.menus.service.MenuService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class MenuServiceImplTest {
    @Autowired
    private MenuService menuService;

    @Test
    public void 카페ID로_메뉴찾기() {
        Long cafeId = 1L;
        List<MenuDTO> menuListByCafeId = menuService.getMenuListByCafeId(cafeId);

        menuListByCafeId.stream().forEach(menu -> System.out.println("menu = " + menu));

    }
}