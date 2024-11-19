package ru.effective_mobile.test_case.web.dto.request.task;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedDtoRequest(@Schema(description = "Task status WAITING, IN_PROCESS, COMPLETED/Статус задачи WAITING, IN_PROCESS, COMPLETED")
                                    TaskStatus updateStatus,

                                    @Schema(description = "Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH")
                                    Priorities priorityStatus,

                                    @Schema(description = "Email assignee/Почта исполнителя")
                                    String assigneeEmail) {
}
