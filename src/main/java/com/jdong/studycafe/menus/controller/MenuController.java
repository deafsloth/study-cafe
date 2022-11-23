package com.jdong.studycafe.menus.controller;

import com.jdong.studycafe.beverages.dto.BeverageDTO;
import com.jdong.studycafe.cafes.dto.CafeDTO;
import com.jdong.studycafe.menus.dto.MenuDTO;
import com.jdong.studycafe.beverages.service.BeverageService;
import com.jdong.studycafe.cafes.service.CafeService;
import com.jdong.studycafe.menus.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final CafeService cafeService;
    private final BeverageService beverageService;


    @GetMapping("/list/{cafeId}")
    public ResponseEntity<Map<String, Object>> findMenusByCafeId(@PathVariable(value = "cafeId") Long cafeId) {

        CafeDTO cafe = cafeService.findCafeById(cafeId);
        List<MenuDTO> menuList = menuService.getMenuListByCafeId(cafeId);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("cafe", cafe);
        resultMap.put("menuList", menuList);

        return ResponseEntity.ok().body(resultMap);
    }

}
