package ru.effective_mobile.test_case.web.dto.request.task;

import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedDtoRequest(TaskStatus updateStatus,
                                    Priorities priorityStatus,
                                    String assigneeEmail) {
}
