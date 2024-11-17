package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;

public interface UserService {

    UserResponseFullDto editUserAccountByAdmin(Long userId, UpdateUserAccountRequestDto updateAccountDto);

    UserResponseFullDto banUserAccount(Long userId);

    UserResponseFullDto unbanUserAccount(Long userId);

    UserResponseFullDto deleteUserAccount(Long userId);

    UserResponseFullDto undeleteUserAccount(Long userId);
}
