package com.jdong.studycafe.orders.repository;

import com.jdong.studycafe.orders.dto.MostOrderDTO;
import com.jdong.studycafe.orders.dto.OrderCountDTO;
import com.jdong.studycafe.orders.dto.QMostOrderDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class OrderQuerydslRepositoryTest {
    @Autowired
    private OrderQuerydslRepository orderQuerydslRepository;

    @Test
    public void testMostOrderList() {
        List<OrderCountDTO> mostOrderList = orderQuerydslRepository.getMostOrderList();
        mostOrderList.stream().forEach(order -> System.out.println("order.getBeverageId() = " + order.getBeverageId()+"order.getCafeId() = " + order.getCafeId()+"order.getCount() = " + order.getCnt()));
    }

    @Test
    public void testMostOrderedMenu() {
        List<MostOrderDTO> mostOrderedMenu = orderQuerydslRepository.getMostOrderedMenu(2L);
        System.out.println("mostOrderedMenu. = " + mostOrderedMenu.get(0).getCnt());
    }

}