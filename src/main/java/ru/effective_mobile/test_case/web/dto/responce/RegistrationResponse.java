package ru.effective_mobile.test_case.web.dto.responce;

import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.Set;

public record RegistrationResponse(Long id,
                                   String email,
                                   String password,
                                   Set<RoleType> roles) {
}
