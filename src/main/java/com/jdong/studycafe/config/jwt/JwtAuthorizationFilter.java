package com.jdong.studycafe.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.repository.MemberRepository;
import com.jdong.studycafe.orders.exception.IsStudyingException;
import com.jdong.studycafe.orders.service.StudyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;
    private StudyService studyService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository,StudyService studyService) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.studyService = studyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("header Authorization = " + header);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getClaim("username").asString();
//        String isStudying = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getClaim("isStudying").asString();
//        System.out.println("isStudying = " + isStudying);
        
        if (username != null) {
            System.out.println("username = " + username);
            Member member = memberRepository.findByUsername(username);

            Boolean isStudying = studyService.isStudying(member.getId());
            if (isStudying == Boolean.TRUE) {
                throw new IsStudyingException(member.getId().toString());
            } else {

                CustomUserDetails userDetails = new CustomUserDetails(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request,response);

    }
}
