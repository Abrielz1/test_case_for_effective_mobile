package ru.effective_mobile.test_case.web.dto.responce.account;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.Set;

public record UserResponseFullDto(@Schema(description = "id user/ид пользователя")
                                  Long id,

                                  @Schema(description = "Email user/Почта пользователя")
                                  String email,

                                  @Schema(description = "Password/Пароль")
                                  String password,

                                  @Schema(description = "User deleted flag /Флаг удалён ли пользователь")
                                  Boolean isDeleted,

                                  @Schema(description = "User banned flag /Флаг заблокирован/забанен ли пользователь")
                                  Boolean isBanned,

                                  @Schema(description = "Roles/Роли")
                                  Set<RoleType> roles) {
}
