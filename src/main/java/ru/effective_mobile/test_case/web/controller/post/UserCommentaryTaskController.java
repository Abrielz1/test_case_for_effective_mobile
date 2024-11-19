package ru.effective_mobile.test_case.web.controller.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.test_case.app.service.UserCommentaryTaskService;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.utils.Update;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryShortUpdateResponseDto;
import java.time.LocalDateTime;

@Tag(name = "UserCommentaryTaskController", description = "Контроллер предоставляющий ручки/handlers для взаимодействие с сущностью Commentary для USER и ADMIN")
@Slf4j
@Validated
@RestController
@RequestMapping("/users/commentaries")
@RequiredArgsConstructor
public class UserCommentaryTaskController {

    private final UserCommentaryTaskService commentaryService;

    @Operation(
            summary = "Добавление комментария (Commentary) к задаче (Task) по id задачи (USER, ADMIN)",
            description = "Позволяет оставить комментарий под задачей"
    )
    @PostMapping("/create/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryShortUpdateResponseDto createPostByUser(@Positive @PathVariable(name = "taskId") Long taskId,
                                                             @Validated(Create.class)@RequestBody CommentaryCreationRequest newCommentary) {

        log.info("%nVia Commentary Controller Post was created post by User with email: %s in to task %s with id %d at time:"
                .formatted(newCommentary.authorEmail(), newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.createPostByUser(taskId, newCommentary);
    }

    @Operation(
            summary = "Изменение комментария (Commentary) к задаче (Task) по id задачи",
            description = "Позволяет изменить комментарий (Commentary) под задачей(Task) (USER, ADMIN)"
    )
    @PutMapping("/update/{taskId}/{commentaryId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryShortUpdateResponseDto updatePostByUser(@Positive @PathVariable(name = "taskId") Long taskId,
                                                             @Positive @PathVariable(name = "commentaryId") Long commentaryId,
                                                             @Validated(Update.class)@RequestBody CommentaryUpdateRequestDto updateCommentary) {

        log.info("%nVia Commentary Controller Post was updated post by User with email: %s in to task %s with id %d at time:"
                .formatted(updateCommentary.authorEmail(), updateCommentary, taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.updatePostByUser(taskId, commentaryId, updateCommentary);
    }
}
