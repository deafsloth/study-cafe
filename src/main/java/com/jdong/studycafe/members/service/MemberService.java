package com.jdong.studycafe.members.service;

import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.dto.ChargeRequestDTO;

public interface MemberService {
    Member chargeSpecialCredit(ChargeRequestDTO chargeRequestDTO, Long memberId);

    Member findMemberById(Long memberId);
}
