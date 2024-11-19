package ru.effective_mobile.test_case.web.dto.responce.account;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDto(@Schema(description = "Email user/Почта пользователя")
                              String email) {
}
