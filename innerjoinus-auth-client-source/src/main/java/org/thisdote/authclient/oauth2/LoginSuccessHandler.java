package org.thisdote.authclient.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.thisdote.authclient.dto.CustomOAuth2User;
import org.thisdote.authclient.jwt.JwtUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static org.thisdote.authclient.common.Constant.OAUTH_RESULT_REDIRECT_URI;
import static org.thisdote.authclient.common.Constant.OAUTH_RESULT_TOKEN_KEY;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Autowired
    public LoginSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String loginCode = customUserDetails.getLoginCode();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        /* TODO
         *  1. accessToken, refreshToken 생성
         *  2. accessToken은 user에게 반환
         *  3. refreshToken은 redis 사용하여 accessToken(key): refreshToken(value) 형태로 저장
        * */

        String token = jwtUtil.createJwt(loginCode, role);

        response.addCookie(createCookie(OAUTH_RESULT_TOKEN_KEY, token));
        response.sendRedirect(OAUTH_RESULT_REDIRECT_URI);
    }

    private Cookie createCookie(String authorization, String token) {

        Cookie cookie = new Cookie(authorization, token);
        cookie.setMaxAge(60*60*60);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
