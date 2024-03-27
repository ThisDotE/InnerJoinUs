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
import org.thisdote.authclient.repository.RefreshTokenEntity;
import org.thisdote.authclient.repository.RefreshTokenRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static org.thisdote.authclient.common.Constant.OAUTH_RESULT_REDIRECT_URI;
import static org.thisdote.authclient.common.Constant.OAUTH_RESULT_TOKEN_KEY;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public LoginSuccessHandler(
            JwtUtil jwtUtil,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
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

        String token = jwtUtil.createJwt(loginCode, role);
        String refreshToken = jwtUtil.createRefreshToken(token);
        RefreshTokenEntity tokenEntity = new RefreshTokenEntity(loginCode, token, refreshToken);

        refreshTokenRepository.save(tokenEntity);

        response.addCookie(createCookie(OAUTH_RESULT_TOKEN_KEY, token));
//        response.sendRedirect(OAUTH_RESULT_REDIRECT_URI);
        response.sendRedirect("http://localhost:5173/login");
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
