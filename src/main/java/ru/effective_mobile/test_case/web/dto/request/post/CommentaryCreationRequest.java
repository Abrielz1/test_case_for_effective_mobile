package ru.effective_mobile.test_case.web.dto.request.post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CommentaryCreationRequest(@NotBlank
                                        String commentaryText,
                                        @NotBlank
                                        @Email
                                        String authorEmail) {
}
