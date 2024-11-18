package ru.effective_mobile.test_case.web.dto.request.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskCreationRequest(@Schema(description = "Header commentary/Заголовок комментария")
                                  @NotBlank
                                  String header,

                                  @Schema(description = "Text commentary/Текст комментария")
                                  @NotBlank
                                  String description,

                                  @Schema(description = "Task status WAITING, IN_PROCESS, COMPLETED/Статус задачи WAITING, IN_PROCESS, COMPLETED")
                                  @NotNull
                                  TaskStatus status,

                                  @Schema(description = "Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH")
                                  @NotNull
                                  Priorities priority,

                                  @Schema(description = "Email author/Почта автора")
                                  @NotBlank
                                  @Email
                                  String authorEmail,

                                  @Schema(description = "Email assignee/Почта исполнителя")
                                  @NotBlank
                                  @Email
                                  String assigneeEmail) {
}
