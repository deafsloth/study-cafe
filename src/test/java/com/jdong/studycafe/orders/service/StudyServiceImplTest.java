package com.jdong.studycafe.orders.service;

import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.dto.StudyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudyServiceImplTest {
    @Autowired
    private StudyService studyService;
    @Test
    void postPremiunStudy() {
        OrderRequestDTO orderRequestDTO = OrderRequestDTO.builder()
                .beverageId(1L)
                .cafeId(1L)
                .chargedTime(100L)
                .cost(1000L)
                .build();
        StudyDTO studyDTO = studyService.postPremiunStudy(orderRequestDTO, 1L);
        System.out.println("studyDTO.getCafeName() = " + studyDTO.getCafeName());

    }

    @Test
    void getStudyByMemberId() {
    }

    @Test
    void delStudyByMemberId() {
    }

    @Test
    void isStudying() {
    }
}