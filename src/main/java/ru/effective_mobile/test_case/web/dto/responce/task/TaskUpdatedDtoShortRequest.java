package ru.effective_mobile.test_case.web.dto.responce.task;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedDtoShortRequest(@NotNull
                                         TaskStatus newStatus,
                                         @NotBlank
                                         @Email
                                         String authorEmail) {
}
