package org.thisdote.authclient.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash(value = "jwtToken", timeToLive = 60*60*24*3)
public class RefreshTokenEntity {

    @Id
    private String loginCode;

    @Indexed
    private String accessToken;

    private String refreshToken;
}
