package com.jdong.studycafe.config.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jdong.studycafe.config.jwt.JwtProperties;
import com.jdong.studycafe.config.oauth.provider.GoogleUser;
import com.jdong.studycafe.config.oauth.provider.OAuthUserInfo;
import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.repository.MemberRepository;
import com.jdong.studycafe.orders.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

//@CrossOrigin(origins = CorsProperties.allowed)
@RestController
@RequiredArgsConstructor
public class JwtCreateController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StudyService studyService;

    @PostMapping("/oauth/jwt/google")
    public String jwtCreate(@RequestBody Map<String, Object> data) {
        System.out.println("jwtCreate ------------- ");
        System.out.println("data.get(\"profileObj\") = " + data.get("profileObj"));
        OAuthUserInfo googleUser = new GoogleUser((Map<String, Object>) data.get("profileObj")); //큰 인터페이스로 묶기(다양한 oauth확장대비)

        Member memberEntity = memberRepository.findByUsername(googleUser.getProvider() + "_" + googleUser.getProviderId());

        if (memberEntity == null) {
            Member memberRequest = Member.builder()
                    .username(googleUser.getProvider() + "_" + googleUser.getProviderId())
                    .password(bCryptPasswordEncoder.encode(JwtProperties.BCRYPT_RAWPASSWORD))
                    .email(googleUser.getEmail())
                    .provider(googleUser.getProvider())
                    .providerId(googleUser.getProviderId())
                    .name(googleUser.getName())
                    .roles("ROLE_USER")
                    .build();

            memberEntity = memberRepository.save(memberRequest);

        }
        Boolean studying = studyService.isStudying(memberEntity.getId());

        String jwtToken = JWT.create()
                .withSubject(memberEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", memberEntity.getId())
                .withClaim("username", memberEntity.getUsername())
//                .withClaim("isStudying", studying.toString())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;

    }
}
