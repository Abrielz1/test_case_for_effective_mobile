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
import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.utils.Update;
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

        log.info("%nVia Admin Controller Admin with get all Tasks list at time:"
                +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAdmin(from, size);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOn(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                  @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks with deleted Tasks only list at time:"
                +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAdminWithIsDeletedOn(from, size);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOff(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks without deleted Tasks list at time:"
                +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAdminWithIsDeletedOff(from, size);
    }

    @GetMapping("/all/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks list by authorId %d at time:"
                .formatted(authorId) +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAuthorIdByAdmin(authorId, from, size);
    }

    @GetMapping("/all/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdmin(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks list by assigneeId %d at time:"
                .formatted(assigneeId) +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAssigneeIdByAdmin(assigneeId, from, size);
    }

    @GetMapping("/task/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedFullDtoResponse getTaskByAuthorIdByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                               @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Admin Controller Admin with get task %d by authorId %d at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now() + "/n");
        return adminService.getTaskByAuthorIdByAdmin(authorId);
    }

    @GetMapping("/task/{assigneeId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedFullDtoResponse getTaskByAssigneeIdByAdmin(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                 @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Admin Controller Admin with get task %d by assigneeId %d at time:"
                .formatted(assigneeId, taskId) +  LocalDateTime.now() + "/n");
        return adminService.getTaskByAssigneeIdByAdmin(assigneeId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCreationDtoResponse createTaskByAdmin(@Validated(Create.class)@RequestBody TaskCreationRequest newTask) {

        log.info("%nVia Admin Controller Admin create task %s at time:"
                .formatted(newTask) +  LocalDateTime.now() + "/n");
        return adminService.createTaskByAdmin(newTask);
    }

    @PutMapping("/update/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedFullDtoResponse updateTaskByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                    @Positive @PathVariable(name = "taskId") Long taskId,
                                                    @Validated(Update.class)@RequestBody TaskUpdatedDtoRequest updateTask) {

        log.info("%nVia Admin Controller Admin with id %d update taskId %d task %s at time:"
                .formatted(authorId, taskId, updateTask) +  LocalDateTime.now() + "/n");
        return adminService.updateTaskByAdmin(authorId, taskId, updateTask);
    }

    @DeleteMapping("/delete/{authorId}/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskCreationDtoResponse deleteTaskByAdmin(@Positive @PathVariable(name = "authorId") Long authorId,
                                                     @Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Admin Controller Admin with id %d delete task %d at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now() + "/n");
        return adminService.deleteTaskByAdmin(authorId, taskId);
    }

    @PutMapping("/user/{userId}")
    public UserResponseFullDto editUserAccountByAdmin(@Positive @PathVariable(name = "userId") Long userId,
                                                      @Validated(Update.class)@RequestBody UpdateUserAccountRequestDto updateAccount) {

        log.info("%nVia Admin Controller Admin with id %d delete task %s at time:"
                .formatted(userId, updateAccount) +  LocalDateTime.now() + "/n");
        return adminService.editUserAccountByAdmin(userId, updateAccount);
    }
}
