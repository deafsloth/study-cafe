package com.jdong.studycafe.members.controller;

import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.dto.ChargeRequestDTO;
import com.jdong.studycafe.members.dto.MemberDTO;
import com.jdong.studycafe.members.service.MemberService;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;
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
@RequestMapping("api/v2/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/charge/special")
    public ResponseEntity<HashMap<String, Object>> postPremiumOrder(
            Authentication authentication,
            @RequestBody @Validated ChargeRequestDTO chargeRequestDTO
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = memberService.chargeSpecialCredit(chargeRequestDTO, userDetails.getMember().getId());

        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .specialCredit(member.getSpecialCredit())
                .generalCredit(member.getGeneralCredit())
                .createTime(member.getCreatedDate())
                .modifiedTime(member.getModifiedDate())
                .build();

        HashMap<String, Object> result = new HashMap<>();
        result.put("chargedCreditMember", memberDTO);
        result.put("message", "charge success");

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getMemberInfo(
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getMember().getId();
        Member member = memberService.findMemberById(memberId);
        
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .specialCredit(member.getSpecialCredit())
                .generalCredit(member.getGeneralCredit())
                .createTime(member.getCreatedDate())
                .modifiedTime(member.getModifiedDate())
                .build();
        HashMap<String, Object> result = new HashMap<>();
        result.put("memberInfo", memberDTO);

        return ResponseEntity.ok().body(result);
    }
}
