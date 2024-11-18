package ru.effective_mobile.test_case.security.jwt.service;

import ru.effective_mobile.test_case.app.model.RefreshToken;
import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> getByRefreshToken(String refreshToken);

    RefreshToken checkRefreshToken(RefreshToken refreshToken);

    RefreshToken create(Long userId);

    void deleteByUserId(Long userId);
}
