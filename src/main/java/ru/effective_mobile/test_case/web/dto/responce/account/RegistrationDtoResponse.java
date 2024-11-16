package ru.effective_mobile.test_case.web.dto.responce.account;

import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.Set;

public record RegistrationDtoResponse(Long id,
                                      String email,
                                      String password,
                                      Set<RoleType> roles) {
}
