package ru.effective_mobile.test_case.web.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CommentaryUpdateRequestDto(@Schema(description = "Header commentary/Заголовок комментария")
                                         String commentaryHeader,

                                         @Schema(description = "Text commentary/Текст комментария")
                                         @NotBlank
                                         String commentaryText,

                                         @Schema(description = "Email author/Почта автора")
                                         @NotBlank
                                         @Email
                                         String authorEmail) {
}
