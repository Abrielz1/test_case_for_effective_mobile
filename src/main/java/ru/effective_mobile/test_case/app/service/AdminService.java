package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import java.util.List;

public interface AdminService {

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOn(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOff(Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdmin(Long authorId, Integer from, Integer size);

    List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdmin(Long assigneeId, Integer from, Integer size);

    TaskUpdatedFullDtoResponse getTaskByAuthorIdByAdmin(Long authorId);

    TaskUpdatedFullDtoResponse getTaskByAssigneeIdByAdmin(Long assigneeId);

    TaskCreationDtoResponse createTaskByAdmin(TaskCreationRequest newTask);

    TaskUpdatedFullDtoResponse updateTaskByAdmin(Long authorId, Long taskId, TaskUpdatedDtoRequest updateTask);

    TaskCreationDtoResponse deleteTaskByAdmin(Long authorId, Long taskId);

    UserResponseFullDto editUserAccountByAdmin(Long userId, UpdateUserAccountRequestDto updateAccount);
}
