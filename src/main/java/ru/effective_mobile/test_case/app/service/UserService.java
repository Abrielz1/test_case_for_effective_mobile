package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.TaskUpdatedDtoResponse;
import java.util.List;

public interface UserService {

    List<TaskCreationDtoResponse> getListOfAllTasksCreatedByUser(Long authorId, Integer from, Integer size);

    TaskCreationDtoResponse getTaskCreatedByUser(Long authorId);

    TaskCreationDtoResponse createTaskByAuthor(Long authorId, Long taskId, TaskCreationRequest updateTask);

    TaskUpdatedDtoResponse updateTaskByAuthor(Long authorId, Long taskId, TaskUpdatedDtoRequest updateTask);

    TaskCreationDtoResponse deleteTaskByAuthor(Long authorId, Long taskId);

    List<TaskCreationDtoResponse> getListOfAllTasksAssignedToUser(Long assigneeId, Integer from, Integer size);

    TaskCreationDtoResponse getTaskAssignedToUser(Long assigneeId);
}
