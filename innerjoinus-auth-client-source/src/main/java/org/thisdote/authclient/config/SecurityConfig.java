package org.thisdote.authclient.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.thisdote.authclient.jwt.JwtFilter;
import org.thisdote.authclient.jwt.JwtUtil;
import org.thisdote.authclient.oauth2.LoginSuccessHandler;
import org.thisdote.authclient.service.OAuth2UserService;

import java.util.Collections;
import java.util.List;

import static org.thisdote.authclient.common.Constant.*;

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

        // cors 설정
        httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                List<String> allowStringList = Collections.singletonList("*");
                List<String> exposedHeaders = List.of(OAUTH_RESULT_TOKEN_KEY, CORS_EXPOSED_HEADER_SET_COOKIE, CORS_EXPOSED_HEADER_COOKIE);
                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOriginPatterns(allowStringList);
                configuration.setAllowedMethods(allowStringList);
                configuration.setAllowedHeaders(allowStringList);
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);
                configuration.setExposedHeaders(exposedHeaders);

                return configuration;
            }
        }));

        // csrf disable
        httpSecurity.csrf(auth -> auth.disable());

        // form 로그인 방식 disable
        httpSecurity.formLogin(auth -> auth.disable());

        // http basic 인증 방식 disable
        httpSecurity.httpBasic(auth -> auth.disable());

        // JWT Filter 추가
        JwtFilter jwtFilter = new JwtFilter(jwtUtil);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterAfter(jwtFilter, OAuth2LoginAuthenticationFilter.class);

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
