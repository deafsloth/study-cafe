package com.jdong.studycafe.common.controller;

import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestApiController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String user(Authentication authentication) {
        System.out.println("/user 시작");
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println("principal : "+userDetails.getMember().getId());
        System.out.println("principal : "+userDetails.getMember().getUsername());
        System.out.println("principal : "+userDetails.getMember().getPassword());
        System.out.println("principal : "+userDetails.getMember().getRoles());

        return (String) userDetails.getMember().getName();
    }
}
