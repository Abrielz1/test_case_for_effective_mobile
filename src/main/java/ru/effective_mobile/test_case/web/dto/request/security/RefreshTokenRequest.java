package ru.effective_mobile.test_case.web.dto.request.security;

import io.swagger.v3.oas.annotations.media.Schema;

public record RefreshTokenRequest(@Schema(description = "Token/Токен")
                                  String refreshToken) {
}
