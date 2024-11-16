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
import ru.effective_mobile.test_case.app.service.AdminService;
import ru.effective_mobile.test_case.web.dto.request.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.TaskUpdatedFullDtoResponse;
import ru.effective_mobile.utils.Create;
import ru.effective_mobile.utils.Update;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("Via Admin Controller Admin with get all tasks list at time:"
                +  LocalDateTime.now());
        return adminService.getAllTasksListByAdmin(from, size);
    }

    @GetMapping("/all/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("Via Admin Controller Admin with get all tasks list by authorId %d at time:"
                .formatted(authorId) +  LocalDateTime.now());
        return adminService.getAllTasksListByAuthorIdByAdmin(authorId, from, size);
    }

    @GetMapping("/all/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdmin(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("Via Admin Controller Admin with get all tasks list by assigneeId %d at time:"
                .formatted(assigneeId) +  LocalDateTime.now());
        return adminService.getAllTasksListByAssigneeIdByAdmin(assigneeId, from, size);
    }

    @GetMapping("/task/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedFullDtoResponse getTaskByAuthorIdByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                               @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("Via Admin Controller Admin with get task %d by authorId %d at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now());
        return adminService.getTaskByAuthorIdByAdmin(authorId);
    }

    @GetMapping("/task/{assigneeId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedFullDtoResponse getTaskByAssigneeIdByAdmin(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                 @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("Via Admin Controller Admin with get task %d by assigneeId %d at time:"
                .formatted(assigneeId, taskId) +  LocalDateTime.now());
        return adminService.getTaskByAssigneeIdByAdmin(assigneeId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCreationDtoResponse createTaskByAdmin(@Validated(Create.class)@RequestBody TaskCreationRequest newTask) {

        log.info("Via Admin Controller Admin create task %s at time:"
                .formatted(newTask) +  LocalDateTime.now());
        return adminService.createTaskByAdmin(newTask);
    }

    @PutMapping("/update/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedDtoResponse updateTaskByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                    @Positive @PathVariable(name = "taskId") Long taskId,
                                                    @Validated(Update.class)@RequestBody TaskUpdatedDtoRequest updateTask) {

        log.info("Via Admin Controller Admin with id %d update taskId%d task %s at time:"
                .formatted(authorId, taskId, updateTask) +  LocalDateTime.now());
        return adminService.updateTaskByAdmin(authorId, taskId, updateTask);
    }

    @DeleteMapping("/delete/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskCreationDtoResponse deleteTaskByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                     @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("Via Admin Controller Admin with id %d delete task %s at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now());
        return adminService.deleteTaskByAdmin(authorId, taskId);
    }
}
