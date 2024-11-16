package ru.effective_mobile.test_case.security.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.security.jwt.service.SecurityService;
import ru.effective_mobile.test_case.security.repository.SecurityRepository;
import ru.effective_mobile.test_case.web.dto.request.account.CreateAccountRequest;
import ru.effective_mobile.test_case.web.dto.request.security.LoginRequest;
import ru.effective_mobile.test_case.web.dto.request.security.RefreshTokenRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.security.LoginDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.security.RefreshTokenDtoResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final SecurityRepository securityRepository;


    @Override
    public UserResponseDto registerUserAccount(CreateAccountRequest newUser) {
        return null;
    }

    @Override
    public LoginDtoResponse loginIntoAccount(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public RefreshTokenDtoResponse refreshTokenRefresh(RefreshTokenRequest request) {
        return null;
    }
}
