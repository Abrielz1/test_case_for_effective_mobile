package ru.effective_mobile.test_case.web.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.List;

public record CreateAccountRequest(@NotBlank
                                   @Email
                                   String email,

                                   @NotBlank
                                   String password,
                                   List<RoleType> roles) {
}
