package org.thisdote.authclient.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thisdote.authclient.dto.CustomOAuth2User;
import org.thisdote.authclient.dto.UserDTO;

import java.io.IOException;

import static org.thisdote.authclient.common.Constant.OAUTH_RESULT_TOKEN_KEY;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // cookie들을 불러온 뒤 Authorization Key에 담긴 쿠키를 찾음
        String authorization = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(OAUTH_RESULT_TOKEN_KEY)) {
                authorization = cookie.getValue();
            }
        }

        // Authorization 확인
        if (authorization == null) {
            log.info("token null...");
            filterChain.doFilter(request, response);

            return;
        }

        String token = authorization;

        /* TODO
         *  1. accessToken 만료 시 redis에 저장된 refreshToken 조회
         *  2. refreshToken 만료 확인
         *  3. refreshToken에서 loginCode 추출 후 해당 loginCode와 role을 사용하여 accessToken 재발행
         *  4. accessToken 발행 후 refreshToken도 재발행
         *  5. accessToken 반환, refreshToken redis에 저장
        * */

        // 토큰 만료 시간 확인
        if (jwtUtil.isExpired(token)) {
            log.info("token expired...");
            filterChain.doFilter(request, response);

            return;
        }

        String loginCode = jwtUtil.getLoginCode(token);
        String role = jwtUtil.getRole(token);

        UserDTO userDTO = new UserDTO();
        userDTO.setLoginCode(loginCode);
        userDTO.setRole(role);

        CustomOAuth2User user = new CustomOAuth2User(userDTO);

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
