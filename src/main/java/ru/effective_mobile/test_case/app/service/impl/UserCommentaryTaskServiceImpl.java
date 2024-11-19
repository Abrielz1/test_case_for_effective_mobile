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
import ru.effective_mobile.test_case.app.service.UserCommentaryTaskService;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.mappers.CommentaryMappers;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryShortUpdateResponseDto;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommentaryTaskServiceImpl implements UserCommentaryTaskService {

    private static final String TEXT_MESSAGE = "Via UserService task was not found";

    private static final String TEXT = "No task to sent!";

    private final CommentaryTaskRepository commentaryRepository;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @Override
    public CommentaryShortUpdateResponseDto createPostByUser(Long taskId, CommentaryCreationRequest newCommentary) {

        User commentator = this.checkUserInByEmail(newCommentary.authorEmail());
        Task taskToComment = this.checkTaskInDb(taskId);
        Commentary newComment = CommentaryMappers.toCommentary(newCommentary, taskToComment, commentator);

        log.info("%nVia Commentary Service Post was created post by User with email: %s in to task with id %d at time:"
                .formatted(newComment.getUser().getEmail(), taskId) +  LocalDateTime.now() + "\n");

        return CommentaryMappers.toCommentaryShortUpdateResponseDto(commentaryRepository.saveAndFlush(newComment));
    }

    @Override
    public CommentaryShortUpdateResponseDto updatePostByUser(Long taskId, Long commentaryId, CommentaryUpdateRequestDto updateCommentary) {

        Commentary fromCommentFromDb = this.checkCommentInDb(taskId, commentaryId, updateCommentary.authorEmail());

        if (updateCommentary.commentaryText() != null) {

            fromCommentFromDb.setCommentaryText(updateCommentary.commentaryText());
            System.out.println("TEXT " + updateCommentary.commentaryText());
        }

        commentaryRepository.saveAndFlush(fromCommentFromDb);

        return CommentaryMappers.toCommentaryShortUpdateResponseDto(fromCommentFromDb);
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

        return userRepository.findUserByEmail(userEmail).orElseThrow(() -> {

            log.info("%nVia UserService userEmail %s was not found".formatted(userEmail));
            return new ObjectNotFoundException("Via UserCommentService user was not found");
        });
    }

    private Commentary checkCommentInDb(Long taskId, Long commentaryId, String email) {
        log.info("%nVia UserCommentService taskId: %d and commentaryId: %d was found".formatted(taskId, commentaryId));

        User author = this.checkUserInByEmail(email);
        return commentaryRepository.findCommentaryByTask_IdAndIdAndUser_Id(taskId, commentaryId, author.getId()).orElseThrow(() -> {

            log.info("%nVia UserService taskId: %d and commentaryId: %d not found".formatted(taskId, commentaryId));
            return new ObjectNotFoundException("Via UserCommentService commentary was not found");
        });
    }
}
