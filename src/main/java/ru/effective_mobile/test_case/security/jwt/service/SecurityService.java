package ru.effective_mobile.test_case.security.jwt.service;

import ru.effective_mobile.test_case.web.dto.request.account.CreateAccountRequest;
import ru.effective_mobile.test_case.web.dto.request.security.LoginRequest;
import ru.effective_mobile.test_case.web.dto.request.security.RefreshTokenRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.security.LoginDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.security.RefreshTokenDtoResponse;

public interface SecurityService {

    UserResponseDto registerUserAccount(CreateAccountRequest newUser);

    LoginDtoResponse loginIntoAccount(LoginRequest loginRequest);

    void logout();

    RefreshTokenDtoResponse refreshTokenRefresh(RefreshTokenRequest request);
}
