package ru.effective_mobile.test_case.security.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.model.RefreshToken;
import ru.effective_mobile.test_case.security.jwt.service.RefreshTokenService;
import ru.effective_mobile.test_case.security.jwt.utils.JwtUtils;
import ru.effective_mobile.test_case.security.repository.RefreshTokenRepository;
import ru.effective_mobile.test_case.security.repository.SecurityRepository;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.exception.exceptions.RefreshTokenException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final SecurityRepository securityRepository;

    private final JwtUtils jwtUtils;

    @Value("${app.jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;

    @Override
    public Optional<RefreshToken> getByRefreshToken(String refreshToken) {

        log.info(("RefreshToken was fond" +
                " via RefreshTokenServiceImpl by it's refreshToken: %s ").formatted(refreshToken));
        return refreshTokenRepository.findByToken(refreshToken);
    }

    @Override
    public RefreshToken checkRefreshToken(RefreshToken refreshToken) {

        log.info(("RefreshToken was fond" +
                " via RefreshTokenServiceImpl by it's refreshToken: %s ").formatted(refreshToken));
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {

            refreshTokenRepository.delete(refreshToken);
            log.info("token is expired via RefreshTokenServiceImpl");
            throw new RefreshTokenException("Refresh token is expired! " + refreshToken.getToken()
                    + "Try reLogin!");
        } else {
           log.info(("via RefreshTokenServiceImpl" +
                   " RefreshToken %s was recreated in checkRefreshToken").formatted(refreshToken));
            return refreshToken;
        }
    }

    @Override
    public RefreshToken create(Long userId) {

        var user = securityRepository.findUserById(userId).orElseThrow(()-> {
            log.info("");
            return new ObjectNotFoundException("");
        });

        var refreshToken = RefreshToken
                .builder()
                .userId(userId)
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration.toMillis()))
                .token(jwtUtils.generateTokenFromUserEmail(user.getEmail(), user))
                .build();


        log.info("via RefreshTokenServiceImpl RefreshToken was reCreated : %s".formatted(refreshToken));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void deleteByUserId(Long userId) {
        log.info("via RefreshTokenServiceImpl user with userId: %d session end here".formatted(userId));
        refreshTokenRepository.deleteByUserId(userId);
    }
}
