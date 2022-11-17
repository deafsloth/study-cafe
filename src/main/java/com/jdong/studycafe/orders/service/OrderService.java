package com.jdong.studycafe.orders.service;

import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.orders.domain.Order;
import com.jdong.studycafe.orders.dto.MostOrderDTO;
import com.jdong.studycafe.orders.dto.OrderCountDTO;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;

import java.util.List;


public interface OrderService {

    OrderDTO postPremiumOrder(OrderRequestDTO orderRequestDTO, Long memberId);

    OrderDTO postNormalOrder(OrderRequestDTO orderRequestDTO, Long memberId);

    List<OrderDTO> getOrderListByMemberId(Long memberId);

    MostOrderDTO getMostOrderedMenu(Long cafeId);

}
