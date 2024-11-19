package ru.effective_mobile.test_case.web.controller.adm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.effective_mobile.test_case.app.service.AdminTaskService;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import ru.effective_mobile.test_case.utils.Create;
import ru.effective_mobile.test_case.utils.Update;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "AdminTaskController", description = "Контроллер предоставляющий ручки/handlers для взаимодействие с сущностью Task для ADMIN")
@Slf4j
@Validated
@RestController
@RequestMapping("/admins/tasks")
@RequiredArgsConstructor
public class AdminTaskController {

    private final AdminTaskService adminService;

    @Operation(
            summary = "Получение всех записей задач (Task), в том числе удалённых (ADMIN)",
            description = "Позволяет получить все записи из БД, включая удалённые (ADMIN)"
    )
    @GetMapping("/all-whole-tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all Tasks list at time:"
                +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAdmin(from, size);
    }

    @Operation(
            summary = "Получение всех записей задач (Task), только удалённых (ADMIN)",
            description = "Позволяет получить все записи из БД, только удалённые (ADMIN)"
    )
    @GetMapping("/all-deleted-on-only")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOnly(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks without deleted Tasks list at time:"
                +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAdminWithIsDeletedOnly(from, size);
    }

    @Operation(
            summary = "Получение всех записей задач (Task), кроме удалённых (ADMIN)",
            description = "Позволяет получить все записи из БД, без удалённых (ADMIN)"
    )
    @GetMapping("/all-deleted-off")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOff(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks with deleted Tasks only list at time:"
                +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAdminWithIsDeletedOff(from, size);
    }

    @Operation(
            summary = "Получение всех записей задач (Task) по id автора, кроме удалённых (ADMIN)",
            description = "Позволяет получить все записи по id автора из БД, включая удалённые (ADMIN)"
    )
    @GetMapping("/all/author-short/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdminWithoutDeleted(@Positive @PathVariable(name = "authorId") Long authorId,
                                                                                           @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                           @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks list by authorId %d at time:"
                .formatted(authorId) +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAuthorIdByAdminWithoutDeletedTasks(authorId, from, size);
    }

    @Operation(
            summary = "Получение всех записей задач (Task) по id автора, в том числе удалённых (ADMIN)",
            description = "Позволяет получить все записи по id автора из БД, включая удалённые (ADMIN)"
    )
    @GetMapping("/all/author-full/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdminWithDeleted(@Positive @PathVariable(name = "authorId") Long authorId,
                                                                                        @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                        @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks list by authorId %d at time:"
                .formatted(authorId) +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAuthorIdByAdminWithDeleted(authorId, from, size);
    }


    @Operation(
            summary = "Получение всех записей задач (Task) по id исполнителя, кроме удалённых (ADMIN)",
            description = "Позволяет получить все записи по id исполнителя из БД, без удалённых (ADMIN)"
    )
    @GetMapping("/all/assignee-short/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdminWithoutDeleted(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get all tasks list by assigneeId %d at time:"
                .formatted(assigneeId) +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAssigneeIdByAdminWithoutDeleted(assigneeId, from, size);
    }

    @Operation(
            summary = "Получение всех записей задач (Task) по id исполнителя, в том числе удалённых (ADMIN)",
            description = "Позволяет получить все записи по id исполнителя из БД, включая удалённые (ADMIN)"
    )
    @GetMapping("/all/assignee-full/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskUpdatedFullDtoResponse> getAllTasksByAssigneeIdByAdminWithDeleted(@Positive @PathVariable(name = "assigneeId") Long assigneeId,
                                                                                      @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                                      @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("%nVia Admin Controller Admin with get tasks by assigneeId %d at time:"
                .formatted(assigneeId) +  LocalDateTime.now() + "/n");
        return adminService.getAllTasksListByAssigneeIdByAdminWithDeleted(assigneeId, from, size);
    }

    @Operation(
            summary = "Получение записи задачи (Task) по id задачи (ADMIN)",
            description = "Позволяет получить запись (Task) из БД по id задачи, кроме удалённых (ADMIN)"
    )
    @GetMapping("/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoResponse getTaskByTaskIdByAdminWithoutDeleted(@Positive @PathVariable(name = "taskId") Long taskId) {

        log.info("%nVia Admin Controller Admin with get tasks %d at time:"
                .formatted(taskId) +  LocalDateTime.now() + "/n");
        return adminService.getTaskByTaskIdByAdmin(taskId);
    }

    @Operation(
            summary = "Создание новой задачи (Task) (ADMIN)",
            description = "Позволяет создать новую (Task) задачу (ADMIN)"
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCreationDtoResponse createTaskByAdmin(@Validated(Create.class)@RequestBody TaskCreationRequest newTask) {

        log.info("%nVia Admin Controller Admin create task %s at time:"
                .formatted(newTask) +  LocalDateTime.now() + "/n");
        return adminService.createTaskByAdmin(newTask);
    }

    @Operation(
            summary = "Изменение задачи (Task) по ее id ( ADMIN)",
            description = "Позволяет изменить задачу (Task) по ее id (ADMIN)"
    )
    @PutMapping("/update/{taskId}/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskUpdatedFullDtoResponse updateTaskByAdmin(@Positive @PathVariable(name = "taskId") Long taskId,
                                                        @Positive @PathVariable(name = "authorId") Long authorId,
                                                        @Validated(Update.class)@RequestBody TaskUpdatedDtoRequest updateTask) {

        log.info("%nVia Admin Controller Admin with id %d update taskId %d task %s at time:"
                .formatted(authorId, taskId, updateTask) +  LocalDateTime.now() + "/n");
        return adminService.updateTaskByAdmin(taskId, authorId, updateTask);
    }

    @Operation(
            summary = "Удаление задачи (Task) по ее id (USER, ADMIN)",
            description = "Позволяет удалить задачу (Task) по ее id (USER, ADMIN)"
    )
    @DeleteMapping("/delete/{taskId}/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskCreationDtoResponse deleteTaskByAdmin(@Positive @PathVariable(name = "taskId") Long taskId,
                                                     @Positive @PathVariable(name = "authorId") Long authorId) {

        log.info("%nVia Admin Controller Admin with id %d delete task %d at time:"
                .formatted(authorId, taskId) +  LocalDateTime.now() + "/n");
        return adminService.deleteTaskByAdmin(taskId, authorId);
    }
}
