package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.Commentary;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.repository.CommentaryTaskRepository;
import ru.effective_mobile.test_case.app.repository.TaskRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.AdminCommentaryService;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.mappers.CommentaryMappers;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCommentaryServiceImpl implements AdminCommentaryService {

    private static final String TEXT_MESSAGE = "Via AdminCommentaryService task was not found";

    private static final String TEXT = "No Commentary to sent!";

    private final TaskRepository taskRepository;

    private final CommentaryTaskRepository commentaryTaskRepository;

    private final UserRepository userRepository;

    @Override
    public CommentaryFullUpdateResponseDto createPostByAdmin(Long taskId, CommentaryCreationRequest newCommentary) {

        Task taskToComment = this.checkTaskInDb(taskId);
        User commentator = this.checkUserInByEmail(newCommentary.authorEmail());
        Commentary newComment = CommentaryMappers.toCommentary(newCommentary, taskToComment, commentator);


        log.info("%nVia Commentary Service Post was created post by Admin in to task %s with id %d at time:"
                .formatted(newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return CommentaryMappers.toCommentaryFullUpdateDtoResponse(commentaryTaskRepository.saveAndFlush(newComment));
    }

    @Override
    public CommentaryFullUpdateResponseDto updatePostByAdmin(Long taskId, Long commentaryId, CommentaryUpdateRequestDto updateCommentary) {

        Commentary commentaryToUpdate = this.checkCommentInDb(taskId, commentaryId);

        log.info("%nVia Commentary Service Post: %s with id: %d was updated post by Admin in to task with id %d at time:"
                .formatted(updateCommentary, commentaryId,taskId) +  LocalDateTime.now() + "\n");
        return CommentaryMappers.toCommentaryFullUpdateDtoResponse(
                commentaryTaskRepository.saveAndFlush(this.updaterCommentByAdmin(updateCommentary, commentaryToUpdate)));
    }

    @Override
    public CommentaryFullUpdateResponseDto deleteCommentaryByAdmin(Long taskId, Long commentaryId) {

        Commentary commentaryToDelete = this.checkCommentInDb(taskId, commentaryId);
        commentaryToDelete.setIsDeleted(true);

        log.info("%nVia Commentary Service Post with id: %d was deleted post by Admin in to task with id: %d at time:"
                .formatted(commentaryId, taskId) +  LocalDateTime.now() + "\n");
        return CommentaryMappers.toCommentaryFullUpdateDtoResponse(commentaryTaskRepository.saveAndFlush(commentaryToDelete));
    }

    private Task checkTaskInDb(Long taskId) {

        Task taskFromDb = taskRepository.findTaskById(taskId).orElseThrow(() -> {

            log.info("%nVia UserCommentService task %d was not found".formatted(taskId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });

        if (Boolean.TRUE.equals(taskFromDb.getIsDeleted())) {
            log.info("%nVia UserCommentService Task requested was deleted at time:"
                    + LocalDateTime.now() + "\n");
            throw new ObjectNotFoundException(TEXT);
        }

        log.info("%nVia UserCommentService task %d was found".formatted(taskId));

        return taskFromDb;
    }

    private User checkUserInByEmail(String userEmail) {

        log.info("%nVia UserCommentService by userEmail user %s was found".formatted(userEmail));

        return userRepository.getUserByMail(userEmail).orElseThrow(() -> {

            log.info("%nVia UserService userEmail %s was not found".formatted(userEmail));
            return new ObjectNotFoundException("Via UserCommentService user was not found");
        });
    }

    private Commentary checkCommentInDb(Long taskId, Long commentaryId) {
        log.info("%nVia UserCommentService taskId: %d and commentaryId: %d was found".formatted(taskId, commentaryId));

        return commentaryTaskRepository.findCommentaryByTask_IdAndId(taskId, commentaryId).orElseThrow(() -> {

            log.info("%nVia UserService taskId: %d and commentaryId: %d not found".formatted(taskId, commentaryId));
            return new ObjectNotFoundException("Via UserCommentService commentary was not found");
        });
    }

    private Commentary updaterCommentByAdmin(CommentaryUpdateRequestDto updateCommentary, Commentary commentaryToUpdate) {

        if (updateCommentary != null) {

            if (updateCommentary.commentaryHeader() != null) {
                commentaryToUpdate.setCommentaryHeader(updateCommentary.commentaryHeader());
            }

            if (Objects.requireNonNull(updateCommentary).commentaryText() != null) {
                commentaryToUpdate.setCommentaryText(updateCommentary.commentaryText());
            }
        }

        return commentaryToUpdate;
    }
}
