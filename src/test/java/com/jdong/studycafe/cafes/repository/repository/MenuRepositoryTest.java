//package com.jdong.studycafe.cafes.repository.repository;
//
//import com.jdong.studycafe.beverages.domain.Beverage;
//import com.jdong.studycafe.cafes.domain.Cafe;
//import com.jdong.studycafe.menus.domain.Menu;
//import com.jdong.studycafe.menus.repository.MenuRepository;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Log4j2
//class MenuRepositoryTest {
//    @Autowired
//    MenuRepository menuRepository;
//
//    @Test
//    public void 메뉴_생성() {
//        Cafe cafeAAA = Cafe.builder().id(1L).build();
//        Cafe cafeBBB = Cafe.builder().id(2L).build();
//
//        Beverage Americano = Beverage.builder().id(1L).build();
//        Beverage Latte = Beverage.builder().id(2L).build();
//
//        Menu menu_00 = Menu.builder().cafe(cafeAAA).beverage(Americano).build();
//        Menu menu_01 = Menu.builder().cafe(cafeAAA).beverage(Latte).build();
//        Menu menu_02 = Menu.builder().cafe(cafeBBB).beverage(Latte).build();
//
//        menuRepository.save(menu_00);
//        menuRepository.save(menu_01);
//        menuRepository.save(menu_02);
//    }
//
//    @Test
//    public void findMenusByCafeId() {
//        Long cafeID = 1L;
//        List<Menu> ofMenusByCafeId = menuRepository.getOfMenusByCafeId(cafeID);
//        ofMenusByCafeId.stream().forEach(menu -> System.out.println("menu = " + menu.getCafe().getId()));
//
//    }
//
//    @Test
//    public void testGetMenuByCafeId() {
//        List<Menu> menus = menuRepository.getOfMenusByCafeId(1L);
//        menus.stream().forEach(menu -> System.out.println("menu = " + menu.getId()));
//
//    }
//
//}