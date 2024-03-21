package org.thisdote.authclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.thisdote.authclient.jwt.JwtUtil;
import org.thisdote.authclient.oauth2.LoginSuccessHandler;
import org.thisdote.authclient.service.OAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2UserService oAuth2UserService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final JwtUtil jwtUtil;

    @Autowired
    public SecurityConfig(
            OAuth2UserService oAuth2UserService,
            LoginSuccessHandler loginSuccessHandler,
            JwtUtil jwtUtil
    ) {
        this.oAuth2UserService = oAuth2UserService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // csrf disable
        httpSecurity.csrf(auth -> auth.disable());

        // form 로그인 방식 disable
        httpSecurity.formLogin(auth -> auth.disable());

        // http basic 인증 방식 disable
        httpSecurity.httpBasic(auth -> auth.disable());

        // oauth2
        httpSecurity.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(oAuth2UserService))
                .successHandler(loginSuccessHandler));

        // 경로별 인가 작업
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated());

        // 세션 stateless
        httpSecurity.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
}
