package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /*
    todo:
     Пользователи могут управлять своими задачами,
     если указаны как исполнитель:
      менять статус,
       оставлять комментарии.
     */

    @Override
    public List<TaskCreationDtoResponse> getListOfAllTasksCreatedByUser(Long authorId, Integer from, Integer size) {
        return null;
    }

    @Override
    public TaskCreationDtoResponse getTaskCreatedByUser(Long authorId) {
        return null;
    }

    @Override
    public TaskCreationDtoResponse createTaskByAuthor(Long authorId, Long taskId, TaskCreationRequest updateTask) {

        log.info("%nVia Controller Post was updated post by User with id %d in to task %s with id %d at time:"
                .formatted(authorId, updateTask, taskId) +  LocalDateTime.now() + "\n");
        return null;
    }

    @Override
    public TaskUpdatedDtoResponse updateTaskByAuthor(Long authorId, Long taskId, TaskUpdatedDtoRequest updateTask) {
        return null;
    }

    @Override
    public TaskCreationDtoResponse deleteTaskByAuthor(Long authorId, Long taskId) {
        return null;
    }

    @Override
    public List<TaskCreationDtoResponse> getListOfAllTasksAssignedToUser(Long assigneeId, Integer from, Integer size) {
        return null;
    }

    @Override
    public TaskCreationDtoResponse getTaskAssignedToUser(Long assigneeId) {
        return null;
    }
}
