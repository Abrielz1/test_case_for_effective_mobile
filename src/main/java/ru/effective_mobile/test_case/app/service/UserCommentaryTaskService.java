package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryUpdateRequestDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryShortUpdateResponseDto;

public interface UserCommentaryTaskService {

    CommentaryShortUpdateResponseDto createPostByUser(Long taskId, CommentaryCreationRequest newCommentary);


    CommentaryShortUpdateResponseDto updatePostByUser(Long taskId, Long commentaryId, CommentaryUpdateRequestDto updateCommentary);
}
