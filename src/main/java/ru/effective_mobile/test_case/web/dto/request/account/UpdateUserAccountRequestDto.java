package ru.effective_mobile.test_case.web.dto.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.List;

public record UpdateUserAccountRequestDto(@Schema(description = "Email author/Почта автора")
                                          String email,

                                          @Schema(description = "Password/Пароль")
                                          String password,

                                          @Schema(description = "Roles/Роли")
                                          List<RoleType> roles) {
}
