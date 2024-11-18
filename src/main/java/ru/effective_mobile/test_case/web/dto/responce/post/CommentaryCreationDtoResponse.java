package ru.effective_mobile.test_case.web.dto.responce.post;

import java.time.LocalDateTime;

public record CommentaryCreationDtoResponse(String commentaryHeader,
                                            String commentaryText,
                                            LocalDateTime creationDate) {
}
