package ru.effective_mobile.test_case.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.test_case.app.service.CommentaryService;

@Slf4j
@Validated
@RestController
@RequestMapping("/commentaries")
@RequiredArgsConstructor
public class CommentaryController {

    private final CommentaryService commentaryService;

    /*

    Администратор может
     оставлять комментарии.

    Пользователи могут оставлять комментарии если указаны как исполнитель:
     оставлять комментарии.
    */
}
