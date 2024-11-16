package ru.effective_mobile.test_case.web.dto.responce.task;

import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;

public record TaskCreationDtoResponse(Long id,
                                      String header,
                                      TaskStatus status,
                                      Priorities priority,
                                      UserResponseDto author,
                                      UserResponseDto assignee) {
}
