package ru.effective_mobile.test_case.web.dto.responce;

import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskUpdatedFullDtoResponse(

                                        String header,

                                        String description,

                                        Priorities priority,

                                        TaskStatus status,

                                        String author,

                                        User assignee) {
}
