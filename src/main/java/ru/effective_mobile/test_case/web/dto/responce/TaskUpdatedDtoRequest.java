package ru.effective_mobile.test_case.web.dto.responce;

import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedDtoRequest(TaskStatus updateStatus,
                                    Priorities priorityStatus,
                                    User assignee) {
}
