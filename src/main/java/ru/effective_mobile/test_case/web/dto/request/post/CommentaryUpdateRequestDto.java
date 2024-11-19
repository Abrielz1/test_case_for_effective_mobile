package ru.effective_mobile.test_case.web.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record CommentaryUpdateRequestDto(@Schema(description = "Header commentary/Заголовок комментария")
                                         String commentaryHeader,

                                         @Schema(description = "Text commentary/Текст комментария")
                                         String commentaryText,

                                         @Schema(description = "Email author/Почта автора")
                                         @Email
                                         String authorEmail) {
}
