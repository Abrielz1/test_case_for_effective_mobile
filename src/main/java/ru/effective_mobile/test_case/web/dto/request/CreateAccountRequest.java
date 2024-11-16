package ru.effective_mobile.test_case.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.Set;

public record CreateAccountRequest(@NotBlank
                                   @Email
                                   String email,

                                   @NotBlank
                                   String password,
                                   Set<RoleType> roles) {
}
