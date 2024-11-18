package ru.effective_mobile.test_case.web.dto.responce.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseDto;

public record TaskCreationDtoResponse(@Schema(description = "id task/ид задачи")
                                      Long id,

                                      @Schema(description = "Header task/Заголовок задачи")
                                      String header,

                                      @Schema(description = "Text commentary/Текст комментария")
                                      @NotBlank
                                      String description,

                                      @Schema(description = "Task status WAITING, IN_PROCESS, COMPLETED/Статус задачи WAITING, IN_PROCESS, COMPLETED")
                                      TaskStatus status,

                                      @Schema(description = "Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH")
                                      Priorities priority,

                                      @Schema(description = "author/автора")
                                      UserResponseDto author,

                                      @Schema(description = "assignee/исполнитель")
                                      UserResponseDto assignee) {
}
