package ru.effective_mobile.test_case.web.controller.adm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.utils.Update;
import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import java.time.LocalDateTime;

@Tag(name = "AdminUserController", description = "Контроллер предоставляющий ручки/handlers для взаимодействие с сущностью User для ADMIN")
@Slf4j
@Validated
@RestController
@RequestMapping("/admins/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @Operation(
            summary = "Редактирование пользователя (USER) по id (Task) (ADMIN)",
            description = "Позволяет редактирование пользователя (ADMIN)"
    )
    @PutMapping("/update/user/{userId}")
    public UserResponseFullDto editUserAccountByAdmin(@Positive @PathVariable(name = "userId") Long userId,
                                                      @Validated(Update.class)@RequestBody UpdateUserAccountRequestDto updateAccountDto) {

        log.info("%nVia Admin Controller Admin with id %d delete task %s at time:"
                .formatted(userId, updateAccountDto) +  LocalDateTime.now() + "/n");
        return userService.editUserAccountByAdmin(userId, updateAccountDto);
    }

    @Operation(
            summary = "Бан/Блокировка пользователя (USER) по id (Task) (ADMIN)",
            description = "Позволяет банить/блокировать пользователя (ADMIN)"
    )
    @PatchMapping("/ban/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseFullDto banUserAccount(@Positive @PathVariable(name = "userId") Long userId) {

        log.info("%nVia Admin Controller User with id %d was banned on server at time:"
                .formatted(userId) +  LocalDateTime.now() + "/n");
        return userService.banUserAccount(userId);
    }

    @Operation(
            summary = "Разбан/Разблокировка пользователя (USER) по id (Task) (ADMIN)",
            description = "Позволяет разбанить/разблокировать пользователя (ADMIN)"
    )
    @PatchMapping("/unban/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseFullDto unbanUserAccount(@Positive @PathVariable(name = "userId") Long userId) {

        log.info("%nVia Admin Controller User with id %d was unbanned on server at time:"
                .formatted(userId) +  LocalDateTime.now() + "/n");
        return userService.unbanUserAccount(userId);
    }

    @Operation(
            summary = "Удаление пользователя (USER) по id (Task) (ADMIN)",
            description = "Позволяет удалить пользователя (ADMIN)"
    )
    @DeleteMapping("/delete/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponseFullDto deleteUserAccount(@Positive @PathVariable(name = "userId") Long userId) {

        log.info("%nVia Admin Controller User with id %d was deleted on server at time:"
                .formatted(userId) +  LocalDateTime.now() + "/n");
        return userService.deleteUserAccount(userId);
    }

    @Operation(
            summary = "Восстановление пользователя (USER) по id (Task) (ADMIN)",
            description = "Позволяет восстановить пользователя (ADMIN)"
    )
    @PatchMapping("/undelete/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseFullDto undeleteUserAccount(@Positive @PathVariable(name = "userId") Long userId) {

        log.info("%nVia Admin Controller User with id %d was undeleted on server at time:"
                .formatted(userId) +  LocalDateTime.now() + "/n");
        return userService.undeleteUserAccount(userId);
    }
}
