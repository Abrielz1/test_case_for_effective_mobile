package ru.effective_mobile.test_case.web.dto.responce.security;

import io.swagger.v3.oas.annotations.media.Schema;

public record RefreshTokenDtoResponse(@Schema(description = "Token access/Токен доступа")
                                      String accessToken,

                                      @Schema(description = "Token/Токен")
                                      String refreshToken) {
}
