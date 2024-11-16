package ru.effective_mobile.test_case.utils.mappers;

import lombok.extern.slf4j.Slf4j;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.utils.exception.exceptions.UnsupportedStateException;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import java.time.LocalDateTime;

@Slf4j
public class TaskMapper {

    private TaskMapper() {
        log.info("Was attempt object creation for utility class %s"
                .formatted(TaskMapper.class.getName()));
        throw new UnsupportedStateException("Utility Class!");
    }

    public static Task toTask(TaskCreationRequest createTask, User author, User assignee) {

        new Task();
        return Task
                .builder()
                .taskHeader(createTask.header())
                .taskDescription(createTask.description())
                .taskStatus(createTask.status())
                .taskPriority(createTask.priority())
                .isDeleted(false)
                .author(author)
                .assignee(assignee)
                .creationDate(LocalDateTime.now())
                .build();
    }


    public static TaskCreationDtoResponse toTaskCreationDtoResponse(Task task) {

        return new TaskCreationDtoResponse(task.getId(),
                                           task.getTaskHeader(),
                                           task.getTaskStatus(),
                                           task.getTaskPriority(),
                                           UserMapper.toUserResponseDto(task.getAuthor()),
                                           UserMapper.toUserResponseDto(task.getAssignee()));
    }

    public static TaskUpdatedDtoResponse toTaskUpdatedDtoResponse(Task task, User assignee) {

        return new TaskUpdatedDtoResponse(task.getTaskStatus(),
                                          task.getTaskPriority(),
                                          assignee);
    }

    public static TaskUpdatedFullDtoResponse toTaskUpdatedFullDtoResponse(Task task){

        return new TaskUpdatedFullDtoResponse(task.getTaskHeader(),
                                              task.getTaskDescription(),
                                              task.getTaskPriority(),
                                              task.getTaskStatus(),
                                              task.getAuthor(),
                                              task.getAssignee());
    }
}