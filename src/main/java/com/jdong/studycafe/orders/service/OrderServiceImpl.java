package com.jdong.studycafe.orders.service;

import com.jdong.studycafe.Members.domain.Member;
import com.jdong.studycafe.Members.repository.MemberRepository;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import com.jdong.studycafe.orders.domain.Order;
import com.jdong.studycafe.orders.dto.OrderCountDTO;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.repository.OrderQuerydslRepository;
import com.jdong.studycafe.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BeverageRepository beverageRepository;
    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;
    private final OrderQuerydslRepository orderQuerydslRepository;

    @Override
    public OrderDTO postPremiumOrder(OrderRequestDTO orderRequestDTO, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow();
        Optional<Cafe> optionalCafe = cafeRepository.findById(orderRequestDTO.getCafeId());
        Cafe cafe = optionalCafe.orElseThrow();
        Optional<Beverage> optionalBeverage = beverageRepository.findById(orderRequestDTO.getBeverageId());
        Beverage beverage = optionalBeverage.orElseThrow();

        Order order = Order.builder()
                .member(member)
                .beverage(beverage)
                .cafe(cafe)
                .chargedTime(orderRequestDTO.getChargedTime())
                .cost(orderRequestDTO.getCost())
                .build();

        Order saved = orderRepository.save(order);

        OrderDTO result = OrderDTO.builder()
                .orderId(saved.getId())
                .cafeId(saved.getCafe().getId())
                .cafeName(saved.getCafe().getName())
                .beverageId(saved.getBeverage().getId())
                .beverageName(saved.getBeverage().getName())
                .mainImageUrl(saved.getBeverage().getMainImageUrl())
                .chargedTime(saved.getChargedTime())
                .cost(saved.getCost())
                .createTime(saved.getCreatedDate())
                .modifiedTime(saved.getModifiedDate())
                .build();
        return result;
    }

    @Override
    public OrderDTO postNormalOrder(OrderRequestDTO orderRequestDTO, Long memberId) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrderListByMemberId(Long memberId) {
        List<OrderDTO> orderListByMemberId = orderQuerydslRepository.findOrderListByMemberId(memberId);

        return orderListByMemberId;
    }

    @Override
    public List<OrderCountDTO> getMostOrderList() {
        List<OrderCountDTO> mostOrderList = orderQuerydslRepository.getMostOrderList();
        return mostOrderList;
    }

}
