package com.jdong.studycafe.menus.service;

import com.jdong.studycafe.menus.dto.MenuDTO;

import java.util.List;

public interface MenuService {
    List<MenuDTO> getMenuListByCafeId(Long cafeId);
}
