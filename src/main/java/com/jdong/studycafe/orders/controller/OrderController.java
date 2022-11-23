package com.jdong.studycafe.orders.controller;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.orders.dto.MostOrderDTO;
import com.jdong.studycafe.orders.dto.OrderCountDTO;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/v2/orders/premium")
    public ResponseEntity<HashMap<String, Object>> postPremiumOrder(
            Authentication authentication,
            @RequestBody @Validated OrderRequestDTO orderRequestDTO
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        OrderDTO orderDTO = orderService.postPremiumOrder(orderRequestDTO, userDetails.getMember().getId());

        HashMap<String, Object> result = new HashMap<>();
        result.put("savedOrder", orderDTO);
        result.put("message", "save order success");

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/v2/orders")
    public ResponseEntity<HashMap<String, Object>> getOrderListByMemberId(
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getMember().getId();
        List<OrderDTO> orderList = orderService.getOrderListByMemberId(memberId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("orderList", orderList);
        result.put("memberId", memberId);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/v1/orders/most/{cafeId}")
    public ResponseEntity<HashMap<String, Object>> getMostOrderList(@PathVariable(value = "cafeId") Long cafeId) {
        MostOrderDTO mostOrderedMenu = orderService.getMostOrderedMenu(cafeId);
        HashMap<String, Object> result = new HashMap<>();
        if (mostOrderedMenu != null) {
            result.put("mostOrderedMenu", mostOrderedMenu);
            result.put("cafeId", cafeId);
        } else {
            result.put("mostOrderedMenu", "판매 기록 없음");
            result.put("cafeId", cafeId);
        }

        return ResponseEntity.ok().body(result);
    }
}
