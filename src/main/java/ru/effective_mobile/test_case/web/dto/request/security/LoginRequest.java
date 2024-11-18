package ru.effective_mobile.test_case.web.dto.request.security;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(@Schema(description = "Email user/Почта юзера")
                           String email,
                           @Schema(description = "Password user/Пароль юзера")
                           String password) {
}
