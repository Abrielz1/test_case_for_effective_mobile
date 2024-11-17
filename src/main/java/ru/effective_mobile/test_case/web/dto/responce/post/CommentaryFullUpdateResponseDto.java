package ru.effective_mobile.test_case.web.dto.responce.post;

import java.time.LocalDateTime;

public record CommentaryFullUpdateResponseDto(Long commentaryId,

                                              String commentaryHeader,

                                              String commentaryText,

                                              String authorEmail,

                                              LocalDateTime creationDate) {
}
