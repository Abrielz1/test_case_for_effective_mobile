package ru.effective_mobile.test_case.web.dto.responce.task;

import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedFullDtoResponse(

                                        String header,

                                        String description,

                                        Priorities priority,

                                        TaskStatus status,

                                        User author,

                                        User assignee) {
}
