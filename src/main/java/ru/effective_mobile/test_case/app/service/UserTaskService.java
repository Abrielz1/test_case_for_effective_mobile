package ru.effective_mobile.test_case.app.service;

import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoShortRequest;
import java.util.List;

public interface UserTaskService {

    List<TaskCreationDtoResponse> getListOfAllTasksCreatedByAuthor(Long authorId, Integer from, Integer size);

    TaskDtoResponse getTaskCreatedByAuthor(Long authorId, Long taskId);

    TaskCreationDtoResponse createTaskByAuthor(TaskCreationRequest createTask);

    TaskUpdatedDtoResponse updateTaskByAuthor(Long taskId, TaskUpdatedDtoShortRequest updateTask);

    TaskCreationDtoResponse deleteTaskByAuthor(Long authorId, Long taskId);

    List<TaskCreationDtoResponse> getListOfAllTasksAssignedToUser(Long assigneeId, Integer from, Integer size);
}
