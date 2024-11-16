package ru.effective_mobile.test_case.utils.mappers;

import lombok.extern.slf4j.Slf4j;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.exception.exceptions.UnsupportedStateException;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;

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
}
