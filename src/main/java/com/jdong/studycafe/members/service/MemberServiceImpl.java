package com.jdong.studycafe.members.service;

import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.dto.ChargeRequestDTO;
import com.jdong.studycafe.members.exception.MemberNotFoundException;
import com.jdong.studycafe.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new MemberNotFoundException(memberId));

        return member;
    }

    @Override
    public Member chargeSpecialCredit(ChargeRequestDTO chargeRequestDTO, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new MemberNotFoundException(memberId));

        int specialCredit = member.getSpecialCredit();
        specialCredit += chargeRequestDTO.getChargeCredit();
        member.setSpecialCredit(specialCredit);
        Member saved = memberRepository.save(member);
        return saved;
    }
}
