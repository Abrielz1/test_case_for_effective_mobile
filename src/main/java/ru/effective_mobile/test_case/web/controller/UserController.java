package ru.effective_mobile.test_case.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
// todo UserController
    /*
    todo:
     Вам необходимо разработать простую систему управления задачами (Task Management System) с использованием Java,
      Spring.
     Система должна обеспечивать создание,
      редактирование,
       удаление,
        просмотр задач.
     Каждая задача должна содержать заголовок, описание,
      статус (например, "в ожидании", "в процессе", "завершено"),
       приоритет (например, "высокий", "средний", "низкий")
        и комментарии, а также автора задачи и исполнителя.
     Реализовать необходимо только API.
     */

    /*
    todo:
     Пользователи могут управлять своими задачами,
     если указаны как исполнитель:
      менять статус,
       оставлять комментарии.
     */

    /*
  todo:
   API должно позволять получать задачи конкретного автора или исполнителя,
   а также все комментарии к ним.
   Необходимо обеспечить фильтрацию и пагинацию вывода.
    */

    // todo:
    // Сервис должен корректно обрабатывать ошибки и возвращать понятные сообщения,
    // а также валидировать входящие данные.
}
