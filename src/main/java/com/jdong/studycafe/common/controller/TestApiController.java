package com.jdong.studycafe.common.controller;

import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.members.repository.MemberRepository;
import com.jdong.studycafe.orders.exception.IsStudyingException;
import com.jdong.studycafe.orders.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/test")
@RequiredArgsConstructor
public class TestApiController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StudyService studyService;

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String user(Authentication authentication) {
        System.out.println("/user 시작");
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Boolean isStudying = studyService.isStudying(userDetails.getMember().getId());
        if (isStudying == Boolean.TRUE) {
            throw new IsStudyingException(userDetails.getMember().getId().toString());
        }
        System.out.println("principal : "+userDetails.getMember().getId());
        System.out.println("principal : "+userDetails.getMember().getUsername());
        System.out.println("principal : "+userDetails.getMember().getPassword());
        System.out.println("principal : "+userDetails.getMember().getRoles());

        return (String) userDetails.getMember().getName();
    }
}
