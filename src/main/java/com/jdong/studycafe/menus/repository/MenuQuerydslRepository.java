package com.jdong.studycafe.menus.repository;

import com.jdong.studycafe.menus.dto.MenuDTO;
import com.jdong.studycafe.menus.dto.QMenuDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jdong.studycafe.beverages.domain.QBeverage.beverage;
import static com.jdong.studycafe.cafes.domain.QCafe.cafe;
import static com.jdong.studycafe.menus.domain.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class MenuQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<MenuDTO> getOfMenusByCafeId(Long cafeId) {
        List<MenuDTO> result = jpaQueryFactory
                .select(new QMenuDTO(
                        menu.id,
                        menu.cafe.id,
                        menu.cafe.name,
                        menu.beverage.id,
                        menu.beverage.name,
                        menu.beverage.price,
                        menu.beverage.mainImageUrl,
                        menu.beverage.isPremium,
                        menu.createdDate,
                        menu.modifiedDate
                ))
                .from(menu)
                .leftJoin(menu.cafe, cafe)
                .leftJoin(menu.beverage, beverage)
                .where(menu.cafe.id.eq(cafeId))
                .fetch();
        return result;
    }
}
