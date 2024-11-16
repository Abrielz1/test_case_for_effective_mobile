package ru.effective_mobile.test_case.web.dto.responce.task;

import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedDtoShortRequest(TaskStatus newStatus) {
}
