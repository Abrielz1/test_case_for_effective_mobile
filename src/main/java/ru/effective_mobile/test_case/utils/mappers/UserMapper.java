package ru.effective_mobile.test_case.utils.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import ru.effective_mobile.test_case.utils.exception.exceptions.UnsupportedStateException;
import ru.effective_mobile.test_case.web.dto.request.account.CreateAccountRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class UserMapper {

    private UserMapper() {
        log.info("Was attempt object creation for utility class %s"
                .formatted(UserMapper.class.getName()));
        throw new UnsupportedStateException("Utility Class!");
    }

    public static UserResponseDto toUserResponseDto(User author) {

        return new UserResponseDto(author.getEmail());
    }

    public static User toUser(CreateAccountRequest newUser, PasswordEncoder passwordEncoder) {

        Set<RoleType> set = new HashSet<>(newUser.roles());

        new User();
        return User
                .builder()
                .email(newUser.email())
                .password(passwordEncoder.encode(newUser.password()))
                .isBanned(false)
                .isDeleted(false)
                .roles(set)
                .build();
    }

    public static UserResponseFullDto toUserFullResponseDto(User user) {

        return new UserResponseFullDto(user.getId(),
                                       user.getEmail(),
                                       user.getPassword(),
                                       user.getIsDeleted(),
                                       user.getIsBanned(),
                                       user.getRoles());
    }
}