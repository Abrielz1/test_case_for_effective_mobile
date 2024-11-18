package ru.effective_mobile.test_case.security.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.RefreshToken;
import ru.effective_mobile.test_case.security.jwt.service.RefreshTokenService;
import ru.effective_mobile.test_case.security.jwt.service.SecurityService;
import ru.effective_mobile.test_case.security.jwt.utils.JwtUtils;
import ru.effective_mobile.test_case.security.repository.SecurityRepository;
import ru.effective_mobile.test_case.utils.exception.exceptions.AlreadyExistsException;
import ru.effective_mobile.test_case.utils.exception.exceptions.RefreshTokenException;
import ru.effective_mobile.test_case.utils.mappers.UserMapper;
import ru.effective_mobile.test_case.web.dto.request.account.CreateAccountRequest;
import ru.effective_mobile.test_case.web.dto.request.security.LoginRequest;
import ru.effective_mobile.test_case.web.dto.request.security.RefreshTokenRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.security.LoginDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.security.RefreshTokenDtoResponse;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final SecurityRepository securityRepository;

    private final RefreshTokenService refreshTokenService;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUserAccount(CreateAccountRequest newUser) {

        if (Objects.equals(Boolean.TRUE, securityRepository.existsByEmail(newUser.email()))) {
        log.info("%nUser with email: %s via SecurityServiceImpl was try to create new acc,".formatted(newUser.email()) +
                " but it's already exists!" +  " at time:"  + LocalDateTime.now());
            throw new AlreadyExistsException("%nUser with" +
                    " entered email: %s already exists!"
                            .formatted(newUser.email()));
        }

        log.info("%nUser with email: %s via SecurityServiceImpl was created new account".formatted(newUser.email()) +
                " at time:"  + LocalDateTime.now());
        return UserMapper.toUserResponseDto(securityRepository.saveAndFlush(UserMapper.toUser(newUser, passwordEncoder)));
    }

    @Override
    public LoginDtoResponse loginIntoAccount(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void logout() {
        var currentPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentPrincipal instanceof AppUserDetails userDetails) {
            refreshTokenService.deleteByUserId(userDetails.getId());
        }
    }

    @Override
    public RefreshTokenDtoResponse refreshTokenRefresh(RefreshTokenRequest request) {

        String requestTokenRefresh = request.refreshToken();

        return refreshTokenService.getByRefreshToken(requestTokenRefresh)
                .map(refreshTokenService::checkRefreshToken)
                .map(RefreshToken::getId)
                .map(userid -> {

                    User user = securityRepository.findUserById(userid).orElseThrow(()-> {
                        log.info("No user via SecurityServiceImpl and refreshTokenRefresh!");
                        return new RefreshTokenException("Exception for userId: %d".formatted(userid));
                    });

                    String token = jwtUtils.generateTokenFromUserName(user.getEmail());
                    return new RefreshTokenDtoResponse(token, refreshTokenService.create(userid).getToken());
                }).orElseThrow(() -> new RefreshTokenException("RefreshToken is not found!"));
    }
}
