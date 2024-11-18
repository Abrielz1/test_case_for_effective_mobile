package ru.effective_mobile.test_case.web.dto.responce.post;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentaryFullUpdateResponseDto(@Schema(description = "id commentary/ид комментария")
                                              Long commentaryId,

                                              @Schema(description = "Header commentary/Заголовок комментария")
                                              String commentaryHeader,

                                              @Schema(description = "Text commentary/Текст комментария")
                                              String commentaryText,

                                              @Schema(description = "Email author/Почта автора")
                                              String authorEmail,

                                              @Schema(description = "Commentary creation date/Дата создания комментария")
                                              LocalDateTime creationDate) {
}
