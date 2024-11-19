package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import java.util.List;

public interface AdminTaskService {

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOnly(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOff(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdminWithoutDeletedTasks(Long authorId, Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdminWithDeleted(Long authorId, Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdminWithoutDeleted(Long assigneeId, Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdminWithDeleted(Long assigneeId, Integer from, Integer size);

    TaskDtoResponse getTaskByTaskIdByAdmin(Long taskId);

    TaskCreationDtoResponse createTaskByAdmin(TaskCreationRequest newTask);

    TaskUpdatedFullDtoResponse updateTaskByAdmin(Long taskId, Long authorId, TaskUpdatedDtoRequest updateTask);

    TaskCreationDtoResponse deleteTaskByAdmin(Long taskId, Long authorId);
}
