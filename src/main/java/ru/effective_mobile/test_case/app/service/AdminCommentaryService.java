package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;

public interface AdminCommentaryService {

    CommentaryFullUpdateResponseDto createPostByAdmin(Long taskId, CommentaryCreationRequest newCommentary);

    CommentaryFullUpdateResponseDto updatePostByAdmin(Long taskId, Long commentaryId, CommentaryUpdateRequestDto updateCommentary);

    CommentaryFullUpdateResponseDto deleteCommentaryByAdmin(Long taskId, Long commentaryId);
}
