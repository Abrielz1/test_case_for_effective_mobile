package ru.effective_mobile.test_case.web.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.test_case.security.jwt.service.SecurityService;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.web.dto.request.account.CreateAccountRequest;
import ru.effective_mobile.test_case.web.dto.request.security.LoginRequest;
import ru.effective_mobile.test_case.web.dto.request.security.RefreshTokenRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.security.LoginDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.security.RefreshTokenDtoResponse;
import java.time.LocalDateTime;

@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecurityService securityService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto registerUserAccount(@Validated(Create.class) @RequestBody CreateAccountRequest newUser) {

        log.info("%nVia AuthController User created account %s at time:"
                .formatted(newUser) +  LocalDateTime.now() + "\n");
        return securityService.registerUserAccount(newUser);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginDtoResponse loginIntoAccount(@RequestBody LoginRequest loginRequest) {

        log.info("%nVia AuthController User login with account %s at time:"
                .formatted(loginRequest) +  LocalDateTime.now() + "\n");
        return securityService.loginIntoAccount(loginRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logoutOfCurrentAccount() { //@AuthenticationPrincipal UserDetails details
        log.info("%nVia AuthController User logout from account at time:"
                +  LocalDateTime.now() + "\n");
        securityService.logout();
        return "";
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenDtoResponse refreshTokenRefresh(@RequestBody RefreshTokenRequest request) {

        log.info("%nVia AuthController RefreshToken refreshed with token %s at time:"
                .formatted(request) +  LocalDateTime.now() + "\n");
        return securityService.refreshTokenRefresh(request);
    }
}
