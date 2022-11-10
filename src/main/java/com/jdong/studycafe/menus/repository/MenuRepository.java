package com.jdong.studycafe.menus.repository;

import com.jdong.studycafe.menus.domain.Menu;
import com.jdong.studycafe.menus.dto.MenuDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
//    @Query("select " +
//            "new MenuDTO(" +
//                "m.id," +
//                "m.cafe.id," +
//                "m.cafe.name," +
//                "m.beverage.id," +
//                "m.beverage.name," +
//                "m.beverage.mainImageUrl," +
//                "m.createdDate," +
//                "m.modifiedDate) " +
//            "from Menu m " +
//            "left join fetch m.cafe c " +
//            "left join fetch m.beverage b " +
//            "where m.cafe.id=:cafeId")
//    List<MenuDTO> getOfMenusByCafeId(Long cafeId);

}
