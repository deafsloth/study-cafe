package com.jdong.studycafe.orders.controller;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.orders.dto.OrderCountDTO;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/premium")
    public ResponseEntity<HashMap<String, Object>> postPremiumOrder(
            Authentication authentication,
            @RequestBody @Valid OrderRequestDTO orderRequestDTO
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        OrderDTO orderDTO = orderService.postPremiumOrder(orderRequestDTO, userDetails.getMember().getId());

        HashMap<String, Object> result = new HashMap<>();
        result.put("savedOrder", orderDTO);
        result.put("message", "save order success");

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("")
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

    @GetMapping("/most")
    public ResponseEntity<HashMap<String, Object>> getMostOrderList() {
        List<OrderCountDTO> mostOrderList = orderService.getMostOrderList();

        HashMap<String, Object> result = new HashMap<>();
        result.put("orderList", mostOrderList);

        return ResponseEntity.ok().body(result);
    }
}
