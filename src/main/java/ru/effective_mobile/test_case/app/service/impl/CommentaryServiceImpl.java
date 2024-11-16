package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.repository.CommentaryRepository;
import ru.effective_mobile.test_case.app.service.AdminService;
import ru.effective_mobile.test_case.app.service.CommentaryService;
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryUpdateResponseDto;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;

    private final UserService userService;

    private final AdminService adminService;


    @Override
    public CommentaryCreationDtoResponse createPostByAdmin(Long adminId, Long taskId, CommentaryCreationRequest newCommentary) {

        log.info("%nVia Commentary Service Post was created post by Admin with id %d in to task %s with id %d at time:"
                .formatted(adminId, newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return null;
    }

    @Override
    public CommentaryCreationDtoResponse createPostByUser(Long userId, Long taskId, CommentaryCreationRequest newCommentary) {

        log.info("%nVia Commentary Service Post was created post by User with id %d in to task %s with id %d at time:"
                .formatted(userId, newCommentary, taskId) +  LocalDateTime.now() + "\n");
        return null;
    }

    @Override
    public CommentaryUpdateResponseDto updatePostByAdmin(Long adminId, Long taskId, CommentaryUpdateRequestDto updateCommentary) {
        return null;
    }

    @Override
    public CommentaryFullUpdateResponseDto updatePostByUser(Long userId, Long taskId, CommentaryUpdateRequestDto updateCommentary) {
        return null;
    }

    @Override
    public CommentaryFullUpdateResponseDto deleteCommentaryByAdmin(Long taskId) {
        return null;
    }
}
