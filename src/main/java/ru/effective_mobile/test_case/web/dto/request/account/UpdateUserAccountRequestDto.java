package ru.effective_mobile.test_case.web.dto.request.account;

import ru.effective_mobile.test_case.app.model.enums.RoleType;
import java.util.List;

public record UpdateUserAccountRequestDto(String email,
                                          String password,
                                          List<RoleType> roles) {
}
