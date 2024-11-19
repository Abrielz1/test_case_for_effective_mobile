package ru.effective_mobile.test_case.web.dto.responce.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedDtoShortRequest(@Schema(description = "Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH")
                                         @NotNull
                                         TaskStatus newStatus,

                                         @Schema(description = "Email author/Почта автора")
                                         @NotBlank
                                         @Email
                                         String authorEmail) {
}
