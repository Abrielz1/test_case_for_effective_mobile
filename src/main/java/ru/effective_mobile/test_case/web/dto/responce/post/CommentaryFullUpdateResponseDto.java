package ru.effective_mobile.test_case.web.dto.responce.post;

import java.time.LocalDateTime;

public record CommentaryFullUpdateResponseDto(String commentaryText,
                                              String authorEmail,
                                              LocalDateTime creationDate) {
}
