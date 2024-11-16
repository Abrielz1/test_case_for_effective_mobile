package ru.effective_mobile.test_case.web.controller;

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
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/tasks/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskCreationDtoResponse> getListOfAllTasksCreatedByAuthor(@Positive @PathVariable(name = "authorId") Long authorId,
                                                                        @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                        @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Controller Author with id %d get list of tasks at time:"
                .formatted(authorId) +  LocalDateTime.now() + "\n");
        return userService.getListOfAllTasksCreatedByAuthor(authorId, from, size);
    }

    @GetMapping("/task/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskCreationDtoResponse getTaskCreatedByAuthor(@Positive @PathVariable(name = "authorId") Long authorId) {

        log.info("%nVia Controller Author with id%d get task at time:"
                .formatted(authorId) +  LocalDateTime.now() + "\n");
        return userService.getTaskCreatedByAuthor(authorId);
    }

    @PostMapping("/create/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskCreationDtoResponse createTaskByAuthor(@Positive @PathVariable(name = "authorId") Long authorId,
                                                      @Validated(Create.class)@RequestBody TaskCreationRequest createTask) {

        log.info("%nVia Controller Author with id %d update task %s at time:"
                .formatted(authorId, createTask) +  LocalDateTime.now() + "\n");
        return userService.createTaskByAuthor(authorId, createTask);
    }

    @PutMapping("/update/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedDtoResponse updateTaskByAuthor(@Positive @PathVariable(name = "authorId") Long authorId,
                                                     @Positive @PathVariable(name = "taskId") Long taskId,
                                                     @Validated(Update.class) @RequestBody TaskUpdatedDtoShortRequest updateTask) {

        log.info("%nVia Controller Author with id %d update task %s at time:"
                .formatted(authorId,updateTask) +  LocalDateTime.now() + "\n");
        return userService.updateTaskByAuthor(authorId, taskId, updateTask);
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

    @GetMapping("/task/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskCreationDtoResponse getTaskAssignedToUser(@Positive @PathVariable(name = "assigneeId") Long assigneeId) {

        log.info("%nVia Controller Assignee with id%d get task at time:"
                .formatted(assigneeId) +  LocalDateTime.now() + "\n");
        return userService.getTaskAssignedToUser(assigneeId);
    }
}
