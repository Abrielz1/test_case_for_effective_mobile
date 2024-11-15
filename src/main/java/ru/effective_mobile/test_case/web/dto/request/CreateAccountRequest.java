package ru.effective_mobile.test_case.web.dto.request;

import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.Set;

public record CreateAccountRequest(String email,
                                   String password,
                                   Set<RoleType> roles) {
}
