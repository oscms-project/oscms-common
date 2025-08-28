package com.osc.oscms.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private long expirationTimeMs;

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String CLAIM_KEY_USERNAME = "username";

    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    public void init() {
        if (secretKey == null || secretKey.isEmpty()
                || secretKey.equals("YourSuperSecretKeyForJWTGenerationWhichIsVeryLongAndSecure12345!")) {
            logger.error(
                    "CRITICAL: JWT Secret Key is not configured properly or is using the default insecure value! Please set a strong jwt.secret in application properties.");
            if (secretKey == null || secretKey.isEmpty()) {
                this.secretKey = "fallback-unsafe-default-secret-key-for-dev-only-change-me";
                logger.warn("WARNING: Using a fallback unsafe JWT secret key. THIS IS NOT FOR PRODUCTION.");
            }
        }
        if (expirationTimeMs <= 0) {
            logger.error(
                    "CRITICAL: JWT Expiration Time (jwt.expiration.ms) is not configured properly (must be > 0)! Using default of 1 hour.");
            this.expirationTimeMs = 3600000; // 默认1小时
        }

        this.algorithm = Algorithm.HMAC256(this.secretKey);
        this.verifier = JWT.require(this.algorithm).build();
        logger.info("JwtUtil initialized. Secret key length: {}. Expiration time: {} ms.", this.secretKey.length(),
                this.expirationTimeMs);
    }

    /**
     * 根据 UserDetails 和实际的 username 生成 JWT Token.
     */
    public String generateToken(UserDetails userDetails, String actualUsername) {
        String userId = userDetails.getUsername();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Date issuedAt = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + expirationTimeMs);

        logger.debug("Generating token for userId: {}, actualUsername: {}, roles: {}, iat: {}, exp: {}",
                userId, actualUsername, roles, issuedAt, expiresAt);

        return JWT.create()
                .withSubject(userId)
                .withClaim(CLAIM_KEY_USER_ID, userId)
                .withClaim(CLAIM_KEY_USERNAME, actualUsername)
                .withClaim(CLAIM_KEY_ROLES, roles)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(this.algorithm);
    }

    private DecodedJWT decodeToken(String token) throws JWTVerificationException {
        return this.verifier.verify(token);
    }

    public String getUserIdFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim(CLAIM_KEY_USER_ID).asString();
        } catch (JWTVerificationException e) {
            logger.warn("Failed to get userId from token: {}", e.getMessage());
            return null;
        }
    }

    public List<String> getRolesFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim(CLAIM_KEY_ROLES).asList(String.class);
        } catch (JWTVerificationException e) {
            logger.warn("Failed to get roles from token: {}", e.getMessage());
            return null;
        }
    }

    public String getActualUsernameFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim(CLAIM_KEY_USERNAME).asString();
        } catch (JWTVerificationException e) {
            logger.warn("Failed to get actualUsername from token: {}", e.getMessage());
            return null;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userIdFromToken = getUserIdFromToken(token);

            if (userIdFromToken == null) {
                return false;
            }

            return userIdFromToken.equals(userDetails.getUsername());
        } catch (Exception e) {
            logger.error("Unexpected error during token validation", e);
            return false;
        }
    }
}




