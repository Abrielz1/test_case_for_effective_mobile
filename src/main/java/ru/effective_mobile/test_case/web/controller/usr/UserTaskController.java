package ru.effective_mobile.test_case.web.controller.usr;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.test_case.app.service.UserTaskService;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.utils.Update;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoShortRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserTaskController {

    private final UserTaskService userService;

    @GetMapping("/tasks/all/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskCreationDtoResponse> getListOfAllTasksCreatedByAuthor(@Positive @PathVariable(name = "authorId") Long authorId,
                                                                          @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                          @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Controller Author with id %d get list of tasks at time:"
                .formatted(authorId) +  LocalDateTime.now() + "\n");
        return userService.getListOfAllTasksCreatedByAuthor(authorId, from, size);
    }

    @GetMapping("/task/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoResponse getTaskCreatedByAuthor(@Positive @PathVariable(name = "authorId") Long authorId,
                                                  @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Controller Author with id%d get task with taskId: %d at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now() + "\n");
        return userService.getTaskCreatedByAuthor(authorId, taskId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public TaskCreationDtoResponse createTaskByAuthor(@Validated(Create.class)@RequestBody TaskCreationRequest createTask) {

        log.info("%nVia Controller Author with email %s update task %s at time:"
                .formatted(createTask.authorEmail(), createTask) +  LocalDateTime.now() + "\n");
        return userService.createTaskByAuthor(createTask);
    }

    @PutMapping("/update/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedDtoResponse updateTaskByAuthor(@Positive @PathVariable(name = "taskId") Long taskId,
                                                     @Validated(Update.class) @RequestBody TaskUpdatedDtoShortRequest updateTask) {

        log.info("%nVia Controller Author with email %s update task %s at time:"
                .formatted(updateTask.authorEmail(), updateTask) +  LocalDateTime.now() + "\n");
        return userService.updateTaskByAuthor(taskId, updateTask);
    }

    @DeleteMapping("/delete/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskCreationDtoResponse deleteTaskByAuthor(@Positive @PathVariable(name = "authorId") Long authorId,
                                                      @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Controller Author with id %d delete task %d at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now() + "\n");
        return userService.deleteTaskByAuthor(authorId, taskId);
    }

    @GetMapping("/tasks/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskCreationDtoResponse> getListOfAllTasksAssignedToUser(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                         @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Controller Assignee with id %d get list of tasks at time:"
                .formatted(assigneeId) +  LocalDateTime.now() + "\n");
        return userService.getListOfAllTasksAssignedToUser(assigneeId, from, size);
    }
}
