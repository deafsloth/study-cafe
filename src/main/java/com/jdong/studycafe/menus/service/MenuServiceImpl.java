package com.jdong.studycafe.menus.service;

import com.jdong.studycafe.menus.dto.MenuDTO;
import com.jdong.studycafe.menus.domain.Menu;
import com.jdong.studycafe.menus.repository.MenuQuerydslRepository;
import com.jdong.studycafe.menus.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class MenuServiceImpl implements MenuService {

    private final MenuQuerydslRepository menuQuerydslRepository;

    @Override
    public List<MenuDTO> getMenuListByCafeId(Long cafeId) {
        List<MenuDTO> menuList = menuQuerydslRepository.getOfMenusByCafeId(cafeId);

        return menuList;
    }
}
