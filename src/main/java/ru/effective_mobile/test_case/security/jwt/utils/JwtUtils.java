package ru.effective_mobile.test_case.security.jwt.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.effective_mobile.test_case.app.entity.User;
import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String someSecretKey;

    @Value("${app.jwt.tokenExpiration}")
    private Duration tokenExpiration;

    public String generateTokenFromUserEmail(String email, User user) {

       return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("email", email)
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tokenExpiration.toMillis()))
                .signWith(SignatureAlgorithm.HS512, someSecretKey)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(someSecretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(someSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Signature is Invalid: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Token is Invalid: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Token is Expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Token is Unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Claims string is Empty: {}", e.getMessage());
        }
        return false;
    }
}
