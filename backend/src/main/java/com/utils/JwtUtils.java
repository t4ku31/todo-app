package com.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

    // 鍵でjwtを検証
    public Claims getPayload(String jwt, SecretKey secretKey) {
        Objects.requireNonNull(secretKey, "Secret key must not be null");
        try {
            if (Objects.isNull(jwt)) {
                throw new IllegalArgumentException("Authentication token is missing.");
            }

            return Jwts.parser()
                    .verifyWith(secretKey)
                    .clockSkewSeconds(10)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Authentication token is invalid.", e);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Authentication token has expired.", e);
        }
    }

    // jwt本体を生成
    public String generateToken(String subject, String audience, int expiresIn,
            SecretKey secretKey) {
        validateInputs(subject, audience, secretKey);

        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + expiresIn * 1000L);

        log.debug("Generating JWT token for subject: {}, audience: {}, issuedAt: {}, expiresIn: {} seconds",
                subject, audience, issuedAt, expiresIn);

        return Jwts.builder()
                .subject(subject)
                .id(UUID.randomUUID().toString())
                .audience()
                .add(audience)
                .and()
                .issuedAt(issuedAt)
                .notBefore(issuedAt)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    // jwtの著名、検証に用いる鍵を生成
    public SecretKey generateSecretKey(String signingKey) {
        if (Objects.isNull(signingKey) || signingKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Authentication signing key is missing.");
        }
        return Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
    }

    // jwtの検証
    private void validateInputs(String subject, String audience, SecretKey secretKey) {
        Objects.requireNonNull(subject, "Token subject must not be null");
        Objects.requireNonNull(audience, "Token audience must not be null");
        Objects.requireNonNull(secretKey, "Secret key must not be null");
    }
}