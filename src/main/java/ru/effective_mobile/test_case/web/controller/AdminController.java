package ru.effective_mobile.test_case.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
// todo AdminController

    /*
    todo:
     Администратор может управлять всеми задачами:
     создавать новые,
      редактировать существующие,
      просматривать,
       удалять,
     менять статус и приоритет,
      назначать исполнителей задачи,
       оставлять комментарии.
    */

        /*
  todo:
   API должно позволять получать задачи конкретного автора или исполнителя,
   а также все комментарии к ним.
   Необходимо обеспечить фильтрацию и пагинацию вывода.
    */
}
