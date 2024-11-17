package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import java.util.List;

public interface AdminTaskService {

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOff(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOnly(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdmin(Long authorId, Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdmin(Long assigneeId, Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getTasksByAssigneeIdByAdmin(Long taskId, Long assigneeId, Integer from, Integer size);

    TaskCreationDtoResponse createTaskByAdmin(TaskCreationRequest newTask);

    TaskUpdatedFullDtoResponse updateTaskByAdmin(Long taskId, Long authorId, TaskUpdatedDtoRequest updateTask);

    TaskCreationDtoResponse deleteTaskByAdmin(Long taskId, Long authorId);

    TaskDtoResponse getTaskByTaskIdByAdmin(Long taskId);
}
