package ru.effective_mobile.test_case.web.dto.responce.task;

import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryDto;
import java.util.List;

public record TaskDtoResponse(String header,
                              TaskStatus status,
                              Priorities priority,
                              UserResponseDto author,
                              UserResponseDto assignee,
                              List<CommentaryDto> list) {
}
