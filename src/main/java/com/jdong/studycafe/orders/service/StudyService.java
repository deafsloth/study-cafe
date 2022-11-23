package com.jdong.studycafe.orders.service;

import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.dto.StudyDTO;

public interface StudyService {
    StudyDTO postPremiunStudy(OrderRequestDTO orderRequestDTO, Long memberId);

    StudyDTO getStudyByMemberId(Long memberId);

    StudyDTO delStudyByMemberId(Long memberId);

    Boolean isStudying(Long memberId);
}
