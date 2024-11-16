package ru.effective_mobile.test_case.web.controller;

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
import ru.effective_mobile.test_case.app.service.CommentaryService;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.utils.Update;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryUpdateResponseDto;
import java.time.LocalDateTime;

@Slf4j
@Validated
@RestController
@RequestMapping("/commentaries")
@RequiredArgsConstructor
public class CommentaryController {

    private final CommentaryService commentaryService;

    @PostMapping("/{adminId}/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryCreationDtoResponse createPostByAdmin(@Positive @PathVariable(name = "adminId") Long adminId,
                                                           @Positive @PathVariable(name = "taskId") Long taskId,
                                                           @Validated(Create.class)@RequestBody CommentaryCreationRequest newCommentary) {

        log.info("%nVia Commentary Controller Post was created post by Admin with id %d in to task %s with id %d at time:"
                .formatted(adminId, newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.createPostByAdmin(adminId, taskId, newCommentary);
    }

    @PostMapping("/{userId}/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryCreationDtoResponse createPostByUser(@Positive @PathVariable(name = "userId") Long userId,
                                                          @Positive @PathVariable(name = "taskId") Long taskId,
                                                          @Validated(Create.class)@RequestBody CommentaryCreationRequest newCommentary) {

        log.info("%nVia Commentary Controller Post was created post by User with id %d in to task %s with id %d at time:"
                .formatted(userId, newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.createPostByUser( userId, taskId, newCommentary);
    }

    @PutMapping("/{adminId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryUpdateResponseDto updatePostByAdmin(@Positive @PathVariable(name = "adminId") Long adminId,
                                                         @Positive @PathVariable(name = "taskId") Long taskId,
                                                         @Validated(Update.class) @RequestBody CommentaryUpdateRequestDto updateCommentary) {

        log.info("%nVia Commentary Controller Post was updated post by User with id %d in to task %s with id %d at time:"
                .formatted(adminId, updateCommentary, taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.updatePostByAdmin(adminId, taskId, updateCommentary);
    }

    @PutMapping("/{userId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryFullUpdateResponseDto updatePostByUser(@Positive @PathVariable(name = "userId") Long userId,
                                                            @Positive @PathVariable(name = "taskId") Long taskId,
                                                            @Validated(Update.class) CommentaryUpdateRequestDto updateCommentary) {

        log.info("%nVia Commentary Controller Post was updated post by User with id %d in to task %s with id %d at time:"
                .formatted(userId, updateCommentary, taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.updatePostByUser(userId, taskId, updateCommentary);
    }

    @DeleteMapping("/delete/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentaryFullUpdateResponseDto deleteCommentaryByAdmin(@Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Commentary Controller Post was deleted post by Admin in task with id %d at time:"
                .formatted(taskId) +  LocalDateTime.now() + "\n");
        return commentaryService.deleteCommentaryByAdmin(taskId);
    }

}
