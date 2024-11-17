package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseFullDto editUserAccountByAdmin(Long userId, UpdateUserAccountRequestDto updateAccountDto) {


        return null;
    }

    @Override
    public UserResponseFullDto banUserAccount(Long userId) {


        return null;
    }

    @Override
    public UserResponseFullDto unbanUserAccount(Long userId) {


        return null;
    }

    @Override
    public UserResponseFullDto deleteUserAccount(Long userId) {


        return null;
    }


    @Override
    public UserResponseFullDto undeleteUserAccount(Long userId) {


        return null;
    }

    private User updateUserFields(UpdateUserAccountRequestDto updateAccountDto, User userFromDb) {


        return null;
    }

    private User checkUserInDb(Long userId) {

        log.info("%nVia UserService author %d was found".formatted(userId));

        return userRepository.findById(userId).orElseThrow(() -> {

            log.info("%nVia UserService author %d was not found".formatted(userId));
            return new ObjectNotFoundException("Via UserService author was not found");
        });
    }
}
