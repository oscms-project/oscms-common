package com.osc.oscms.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier; // 导入 JWTVerifier
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException; // 导入通用的验证异常
// import com.osc.oscms.domain.User; // 如果 generateToken 中不直接使用 User 实体，可以移除
import com.auth0.jwt.interfaces.DecodedJWT; // 导入 DecodedJWT
import org.slf4j.Logger; // 导入 Logger
import org.slf4j.LoggerFactory; // 导入 LoggerFactory
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct; // 导入 PostConstruct for JSR-250

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class); // 添加 Logger

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private long expirationTimeMs;

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String CLAIM_KEY_USERNAME = "username"; // <--- 新增 Claim Key for username

    private Algorithm algorithm; // 将 Algorithm 实例缓存，避免重复创建
    private JWTVerifier verifier;  // 将 JWTVerifier 实例缓存

    @PostConstruct
    public void init() {
        // 在 Bean 初始化后，根据 secretKey 创建 Algorithm 和 JWTVerifier
        // 这样它们只需要创建一次
        if (secretKey == null || secretKey.isEmpty() || secretKey.equals("YourSuperSecretKeyForJWTGenerationWhichIsVeryLongAndSecure12345!")) {
            logger.error("CRITICAL: JWT Secret Key is not configured properly or is using the default insecure value! Please set a strong jwt.secret in application properties.");
            if (secretKey == null || secretKey.isEmpty()) {
                this.secretKey = "fallback-unsafe-default-secret-key-for-dev-only-change-me";
                logger.warn("WARNING: Using a fallback unsafe JWT secret key. THIS IS NOT FOR PRODUCTION.");
            }
        }
        if (expirationTimeMs <= 0) {
            logger.error("CRITICAL: JWT Expiration Time (jwt.expiration.ms) is not configured properly (must be > 0)! Using default of 1 hour.");
            this.expirationTimeMs = 3600000; // 默认1小时
        }

        this.algorithm = Algorithm.HMAC256(this.secretKey);
        this.verifier = JWT.require(this.algorithm)
                // 如果您在生成 token 时设置了 issuer，这里也需要配置 .withIssuer("your-issuer")
                .build();
        logger.info("JwtUtil initialized. Secret key length: {}. Expiration time: {} ms.", this.secretKey.length(), this.expirationTimeMs);
    }

    /**
     * 根据 UserDetails 和实际的 username 生成 JWT Token.
     *
     * @param userDetails 包含用户认证信息的 UserDetails 对象 (其 getUsername() 返回 userId)
     * @param actualUsername 用户的显示名或昵称 (User 实体中的 username 字段)
     * @return 生成的 JWT Token 字符串
     */
    public String generateToken(UserDetails userDetails, String actualUsername) { // <--- 新增 actualUsername 参数
        String userId = userDetails.getUsername(); // 这是登录ID

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Date issuedAt = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + expirationTimeMs);

        logger.debug("Generating token for userId: {}, actualUsername: {}, roles: {}, iat: {}, exp: {}",
                userId, actualUsername, roles, issuedAt, expiresAt);

        return JWT.create()
                .withSubject(userId) // JWT 的主题，用户的唯一登录标识
                .withClaim(CLAIM_KEY_USER_ID, userId) // 自定义 Claim: 存储 userId
                .withClaim(CLAIM_KEY_USERNAME, actualUsername) // <--- 新增: 存储实际的 username
                .withClaim(CLAIM_KEY_ROLES, roles)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(this.algorithm);
    }

    private DecodedJWT decodeToken(String token) throws JWTVerificationException {
        // 私有辅助方法，用于解码和基础验证 (签名, 过期等)
        return this.verifier.verify(token); // 使用缓存的 verifier
    }

    public String getUserIdFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            // return decodedJWT.getSubject(); // 如果 subject 就是 userId
            return decodedJWT.getClaim(CLAIM_KEY_USER_ID).asString(); // 从自定义 claim 获取
        } catch (JWTVerificationException e) {
            logger.warn("Failed to get userId from token: {}", e.getMessage());
            return null; // 在验证失败时返回 null
        }
    }

    public List<String> getRolesFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim(CLAIM_KEY_ROLES).asList(String.class);
        } catch (JWTVerificationException e) {
            logger.warn("Failed to get roles from token: {}", e.getMessage());
            return null; // 或返回 Collections.emptyList();
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
            final String userIdFromToken = getUserIdFromToken(token); // 这个方法内部已经处理了基本的 JWTVerificationException

            if (userIdFromToken == null) {
                return false;
            }

            return userIdFromToken.equals(userDetails.getUsername());
        } catch (Exception e) { // 捕获任何在 getUserIdFromToken 之外可能发生的意外（理论上不应发生）
            logger.error("Unexpected error during token validation", e);
            return false;
        }
    }
}