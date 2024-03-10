package org.thisdote.innerjoinus.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.thisdote.innerjoinus.user.dto.UserDTO;
import org.thisdote.innerjoinus.user.query.service.UserQueryService;
import org.thisdote.innerjoinus.user.vo.RequestLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserQueryService userQueryService;
    private final Environment environment;

    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            UserQueryService userQueryService,
            Environment environment
    ) {
        super(authenticationManager);
        this.userQueryService = userQueryService;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getUserId(), creds.getUserPassword(), new ArrayList<>()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        System.out.println("시크릿 키: " + environment.getProperty("token.secret"));

        String userId = ((User) authResult.getPrincipal()).getUsername();
        UserDTO userDTO = userQueryService.selectUserByUserId(userId);
        long expirationTime = System.currentTimeMillis() +
                Long.parseLong(Objects.requireNonNull(environment.getProperty("token.expiration_time")));
        Date expirationDate = new Date(expirationTime);

        String token = Jwts.builder()
                .setSubject(userDTO.getUserId())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDTO.getUserId());
    }
}
