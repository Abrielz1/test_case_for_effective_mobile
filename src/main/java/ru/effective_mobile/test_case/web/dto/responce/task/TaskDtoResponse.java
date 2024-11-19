package ru.effective_mobile.test_case.web.dto.responce.task;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryDto;
import java.util.List;

public record TaskDtoResponse(@Schema(description = "Header task/Заголовок задачи")
                              String header,

                              @Schema(description = "Task status WAITING, IN_PROCESS, COMPLETED/Статус задачи WAITING, IN_PROCESS, COMPLETED")
                              TaskStatus status,

                              @Schema(description = "Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH")
                              Priorities priority,

                              @Schema(description = "author/автора")
                              UserResponseDto author,

                              @Schema(description = "assignee/исполнитель")
                              UserResponseDto assignee,

                              @Schema(description = "Commentary Dto list/Лист комментариев")
                              List<CommentaryDto> list) {
}
