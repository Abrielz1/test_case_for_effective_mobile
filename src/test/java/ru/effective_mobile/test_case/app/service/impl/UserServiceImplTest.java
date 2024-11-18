package ru.effective_mobile.test_case.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private UserResponseFullDto userResponseFullDto;

    @BeforeEach
    void setUp() {

        user = User
                .builder()
                .id(1L)
                .email("user@mail.com")
                .password("123")
                .isDeleted(false)
                .isBanned(false)
                .roles(new HashSet<>(RoleType.ROLE_ADMIN.ordinal()))
                .tasksAssignedToUser(new ArrayList<>())
                .tasksAssignedToUser(new ArrayList<>())
                .commentaryList(new ArrayList<>())
                .build();

        userResponseFullDto = new UserResponseFullDto(1L,
                                                    "a@m.com",
                                                    "123",
                                                    false,
                                                    false,
                                                    new HashSet<>(RoleType.ROLE_ADMIN.ordinal()));
    }

    @Test
    @DisplayName("editUserAccountByAdmin")
    void When_editUserAccountByAdmin_ThenReceivedUpdatedUserWithNewEmail() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        when(userRepository.saveAndFlush(any(User.class)))
                .thenReturn(user);

        UpdateUserAccountRequestDto updateUserAccountRequestDto = new UpdateUserAccountRequestDto("a@m.com", null, null);

        UserResponseFullDto userResponseFullDto1 = userService.editUserAccountByAdmin(1L, updateUserAccountRequestDto);

        assertEquals("a@m.com", userResponseFullDto1.email(), "MUST BE -> a@m.com");
        assertEquals("123", userResponseFullDto1.password(), "MUST BE -> 123");
    }

    @Test
    @DisplayName("banUserAccount")
    void When_banUserAccount_ThenReceivedUpdatedUserWithIsBannedTrue() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        when(userRepository.saveAndFlush(any(User.class)))
                .thenReturn(user);

        userResponseFullDto =  userService.banUserAccount(1L);

        assertEquals(true, userResponseFullDto.isBanned(), "MUST BE -> true");
    }

    @Test
    @DisplayName("unbanUserAccount")
    void When_unbanUserAccount_ThenReceivedUpdatedUserWithIsBannedFalse() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        when(userRepository.saveAndFlush(any(User.class)))
                .thenReturn(user);

        userResponseFullDto =  userService.unbanUserAccount(1L);

        assertEquals(false, userResponseFullDto.isBanned(), "MUST BE -> false");
    }

    @Test
    @DisplayName("deleteUserAccount")
    void When_deleteUserAccount_ThenReceivedUpdatedUserWithIsDeletedTrue() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        when(userRepository.saveAndFlush(any(User.class)))
                .thenReturn(user);

        userResponseFullDto =  userService.deleteUserAccount(1L);

        assertEquals(true, userResponseFullDto.isDeleted(), "MUST BE -> true");
    }

    @Test
    @DisplayName("undeleteUserAccount")
    void When_undeleteUserAccount_ThenReceivedUpdatedUserWithIsDeletedFalse() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        when(userRepository.saveAndFlush(any(User.class)))
                .thenReturn(user);

        userResponseFullDto =  userService.undeleteUserAccount(1L);

        assertEquals(false, userResponseFullDto.isDeleted(), "MUST BE -> false");
    }
}