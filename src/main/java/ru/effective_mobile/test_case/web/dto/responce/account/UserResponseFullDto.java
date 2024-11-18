package ru.effective_mobile.test_case.web.dto.responce.account;

import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.Set;

public record UserResponseFullDto(Long id,
                                  String email,
                                  String password,
                                  Boolean isDeleted,
                                  Boolean isBanned,
                                  Set<RoleType> roles) {
}
