package ru.effective_mobile.test_case.web.dto.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.List;

public record CreateAccountRequest(@Schema(description = "Email user/Почта юзера")
                                   @Email
                                   @NotBlank
                                   String email,

                                   @NotBlank
                                   @Schema(description = "Password/Пароль")
                                   String password,

                                   @Schema(description = "Roles/Роли")
                                   List<RoleType> roles) {
}
