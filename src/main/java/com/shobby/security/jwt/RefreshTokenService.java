package com.shobby.security.jwt;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final long REFRESH_TOKEN_TTL_DAYS = 7;
    private static final String PREFIX = "refresh_token:";

    private final StringRedisTemplate stringRedisTemplate;

    public String create(String username) {
        String refreshToken = UUID.randomUUID().toString();
        String hashedToken = hash(refreshToken);
        stringRedisTemplate.opsForValue()
                .set(PREFIX+username,
                        hashedToken,
                        REFRESH_TOKEN_TTL_DAYS,
                        TimeUnit.DAYS);
        return refreshToken;
    }

    public boolean validate(String username, String token) {
        String storedToken = stringRedisTemplate
                .opsForValue()
                .get(PREFIX+username);
        if (storedToken == null) {
            throw new InvalidRefreshTokenException("RefreshToken is invalid");
        }
        return storedToken.equals(hash(token));
    }

    public void revoke(String username) {
         stringRedisTemplate.delete(PREFIX+username);
    }

    public String getUsernameByRefreshToken(String refreshToken) {
        String hashedToken = hash(refreshToken);
        Set<String> keys = stringRedisTemplate.keys(PREFIX+"*");
        for (String key : keys) {
            String storedToken = stringRedisTemplate.opsForValue().get(key);
            if (hashedToken.equals(storedToken)) {
                return key.substring(PREFIX.length());
            }
        }
        throw new InvalidRefreshTokenException("Invalid refresh token.");
    }

    public String hash(String token) {
        return DigestUtils.sha256Hex(token);
    }


}
