package com.jdong.studycafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern(CorsProperties.allowedOriginPattern); // Access-Control-Allow-Origin
        config.addAllowedHeader(CorsProperties.allowedHeader);  // Access-Control-Request-Headers
        config.addAllowedMethod(CorsProperties.allowedMethod); // Access-Control-Request-Method

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
