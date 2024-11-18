package ru.effective_mobile.test_case.web.dto.request.task;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;

public record TaskCreationRequest(@NotBlank
                                  String header,

                                  @NotBlank
                                  String description,

                                  @NotNull
                                  TaskStatus status,

                                  @NotNull
                                  Priorities priority,

                                  @NotBlank
                                  @Email
                                  String authorEmail,

                                  @NotBlank
                                  @Email
                                  String assigneeEmail) {
}
