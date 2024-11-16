package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryUpdateResponseDto;

public interface CommentaryService {

    CommentaryCreationDtoResponse createPostByAdmin(Long adminId, Long taskId, CommentaryCreationRequest newCommentary);

    CommentaryCreationDtoResponse createPostByUser(Long userId, Long taskId, CommentaryCreationRequest newCommentary);

    CommentaryUpdateResponseDto updatePostByAdmin(Long adminId, Long taskId, CommentaryUpdateRequestDto updateCommentary);

    CommentaryFullUpdateResponseDto updatePostByUser(Long userId, Long taskId, CommentaryUpdateRequestDto updateCommentary);

    CommentaryFullUpdateResponseDto deleteCommentaryByAdmin(Long taskId);
}
