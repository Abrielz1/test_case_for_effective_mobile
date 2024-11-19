package ru.effective_mobile.test_case.web.dto.responce.security;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record LoginDtoResponse(@Schema(description = "id user/ид пользователя")
                               Long id,

                               @Schema(description = "Email author/Почта автора")
                               String email,

                               @Schema(description = "Roles/Роли")
                               List<String> roles,

                               @Schema(description = "Token access/Токен доступа")
                               String token) {
}
