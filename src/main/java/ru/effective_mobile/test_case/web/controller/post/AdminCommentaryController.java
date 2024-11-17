package ru.effective_mobile.test_case.web.controller.post;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.test_case.app.service.AdminCommentaryService;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.utils.Update;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;
import java.time.LocalDateTime;

@Slf4j
@Validated
@RestController
@RequestMapping("/admins/commentaries")
@RequiredArgsConstructor
public class AdminCommentaryController {

    private final AdminCommentaryService adminCommentaryService;

    @PostMapping("/create/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryFullUpdateResponseDto createPostByAdmin(@Positive @PathVariable(name = "taskId") Long taskId,
                                                             @Validated(Create.class)@RequestBody CommentaryCreationRequest newCommentary) {

        log.info("%nVia Commentary Controller Post was created post by Admin in to task %s with id %d at time:"
                .formatted(newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return adminCommentaryService.createPostByAdmin(taskId, newCommentary);
    }
    @PutMapping("/update/{taskId}/{commentaryId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryFullUpdateResponseDto updatePostByAdmin(@Positive @PathVariable(name = "taskId") Long taskId,
                                                             @Positive @PathVariable(name = "commentaryId") Long commentaryId,
                                                             @Validated(Update.class) @RequestBody CommentaryUpdateRequestDto updateCommentary) {

        log.info("%nVia Commentary Controller Post was updated post by User in to task %s with id %d at time:"
                .formatted(updateCommentary, taskId) +  LocalDateTime.now() + "\n");
        return adminCommentaryService.updatePostByAdmin(taskId, commentaryId, updateCommentary);
    }

    @DeleteMapping("/delete/{taskId}/{commentaryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentaryFullUpdateResponseDto deleteCommentaryByAdmin(@Positive @PathVariable(name = "taskId") Long taskId,
                                                                   @Positive @PathVariable(name = "commentaryId") Long commentaryId) {

        log.info("%nVia Commentary Controller Post was deleted post by Admin in task with id %d at time:"
                .formatted(taskId) +  LocalDateTime.now() + "\n");
        return adminCommentaryService.deleteCommentaryByAdmin(taskId, commentaryId);
    }
}
