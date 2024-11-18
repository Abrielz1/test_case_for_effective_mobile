package ru.effective_mobile.test_case.web.dto.responce.post;

import java.time.LocalDateTime;

public record CommentaryShortUpdateResponseDto(String commentaryHeader,

                                               String commentaryText,

                                               String authorEmail,

                                               LocalDateTime creationDate) {
}