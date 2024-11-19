package ru.effective_mobile.test_case.web.dto.responce.task;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedFullDtoResponse(@Schema(description = "Header commentary/Заголовок комментария")
                                         String header,

                                         @Schema(description = "Text commentary/Текст комментария")
                                         String description,

                                         @Schema(description = "Task status WAITING, IN_PROCESS, COMPLETED/Статус задачи WAITING, IN_PROCESS, COMPLETED")
                                         Priorities priority,

                                         @Schema(description = "Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH")
                                         TaskStatus status,

                                         @Schema(description = "Email author/Почта автора")
                                         String author,

                                         @Schema(description = "Email assignee/Почта исполнителя")
                                         String assignee) {
}
