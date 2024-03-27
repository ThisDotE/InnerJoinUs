package org.thisdote.authclient.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.thisdote.authclient.common.Constant.JWT_ATTR_LOGIN_CODE;
import static org.thisdote.authclient.common.Constant.JWT_ATTR_ROLE;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expirationTime;
    private final long reFreshTokenExpirationTime;

    public JwtUtil(
            @Value("${token.secret}") String secret,
            @Value("${token.expiration_time}") long expirationTime,
            @Value("${spring.data.redis.expiration_time}") long reFreshTokenExpirationTime
    ) {
        byte[] key = secret.getBytes(StandardCharsets.UTF_8);
        String algorithm = Jwts.SIG.HS256.key().build().getAlgorithm();

        this.secretKey = new SecretKeySpec(key, algorithm);
        this.expirationTime = expirationTime;
        this.reFreshTokenExpirationTime = reFreshTokenExpirationTime;
    }

    public String getLoginCode(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(JWT_ATTR_LOGIN_CODE, String.class);
    }

    public String getRole(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(JWT_ATTR_ROLE, String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public String createJwt(String loginCode, String role) {
        return Jwts
                .builder()
                .claim(JWT_ATTR_LOGIN_CODE, loginCode)
                .claim(JWT_ATTR_ROLE, role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(String accessToken) {
        return Jwts
                .builder()
                .claim("accessToken", accessToken)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + reFreshTokenExpirationTime))
                .signWith(secretKey)
                .compact();
    }
}
