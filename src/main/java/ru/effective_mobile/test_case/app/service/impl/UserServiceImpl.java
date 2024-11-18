package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.mappers.UserMapper;
import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String TEXT = " and account such as: %s  at time:";

    private final UserRepository userRepository;

    @Override
    public UserResponseFullDto editUserAccountByAdmin(Long userId, UpdateUserAccountRequestDto updateAccountDto) {

        log.info(("%nVia AdminUserService User account was updated by Admin with userId %d" +
                TEXT)
                .formatted(userId,  updateAccountDto) + LocalDateTime.now() + "\n");
        return UserMapper.toUserFullResponseDto(
                userRepository.saveAndFlush(
                        this.editUserByAdmin(updateAccountDto, userId)));
    }

    @Override
    public UserResponseFullDto banUserAccount(Long userId) {

        User userFromDb = this.checkUserInDb(userId);
        userFromDb.setIsBanned(true);
        log.info(("%nVia AdminUserService User account was banned by Admin with userId %d" +
                TEXT)
                .formatted(userId, userFromDb) + LocalDateTime.now() + "\n");
        return UserMapper.toUserFullResponseDto(
                userRepository.saveAndFlush(userFromDb));
    }

    @Override
    public UserResponseFullDto unbanUserAccount(Long userId) {

        User userFromDb = this.checkUserInDb(userId);
        userFromDb.setIsBanned(false);
        log.info(("%nVia AdminUserService User account was unbanned by Admin with userId %d" +
                TEXT)
                .formatted(userId, userFromDb) + LocalDateTime.now() + "\n");
        return UserMapper.toUserFullResponseDto(
                userRepository.saveAndFlush(userFromDb));
    }

    @Override
    public UserResponseFullDto deleteUserAccount(Long userId) {

        User userFromDb = this.checkUserInDb(userId);
        userFromDb.setIsDeleted(true);
        log.info(("%nVia AdminUserService User account was deleted by Admin with userId %d" +
                TEXT)
                .formatted(userId, userFromDb) + LocalDateTime.now() + "\n");
        return UserMapper.toUserFullResponseDto(
                userRepository.saveAndFlush(userFromDb));
    }

    @Override
    public UserResponseFullDto undeleteUserAccount(Long userId) {

        User userFromDb = this.checkUserInDb(userId);
        userFromDb.setIsDeleted(false);
        log.info(("%nVia AdminUserService User account was undeleted by Admin with userId %d" +
                TEXT)
                .formatted(userId, userFromDb) + LocalDateTime.now() + "\n");
        return UserMapper.toUserFullResponseDto(
                userRepository.saveAndFlush(userFromDb));
    }

    private User checkUserInDb(Long userId) {

        log.info("%nVia AdminUserService user %d was found".formatted(userId));

        return userRepository.findById(userId).orElseThrow(() -> {

            log.info("%nVia AdminUserService user %d was not found".formatted(userId));
            return new ObjectNotFoundException("Via AdminUserService user was not found");
        });
    }

    private User editUserByAdmin(UpdateUserAccountRequestDto updateUserAccountDto, Long userId) {

        User userInDb = this.checkUserInDb(userId);

        if (updateUserAccountDto.email() != null) {
            userInDb.setEmail(updateUserAccountDto.email());
        }

        if (updateUserAccountDto.password() != null) {
            userInDb.setPassword(updateUserAccountDto.password());
        }

        if (!updateUserAccountDto.roles().isEmpty()) {
            Set<RoleType> roleTypeSet= new HashSet<>(updateUserAccountDto.roles());
            userInDb.setRoles(roleTypeSet);
        }

        log.info("%nVia UserService author %s was edited by Admin".formatted(userInDb));
        return userInDb;
    }
}
