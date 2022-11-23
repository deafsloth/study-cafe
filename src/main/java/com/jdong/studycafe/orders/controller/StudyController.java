package com.jdong.studycafe.orders.controller;

import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.orders.dto.MostOrderDTO;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.dto.StudyDTO;
import com.jdong.studycafe.orders.service.OrderService;
import com.jdong.studycafe.orders.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api")
public class StudyController {

//    private final OrderService orderService;
    private final StudyService studyService;

    @PostMapping("/v2/study/isStudy")
    public ResponseEntity<HashMap<String, Object>> postPremiumOrder(
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Boolean studying = studyService.isStudying(userDetails.getMember().getId());
        HashMap<String, Object> result = new HashMap<>();
        result.put("isStudy", studying);

        return ResponseEntity.ok().body(result);
    }
}
