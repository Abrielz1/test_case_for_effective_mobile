package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.repository.TaskRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.UserTaskService;
import ru.effective_mobile.test_case.utils.exception.exceptions.BadRequestException;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.mappers.CommentaryMappers;
import ru.effective_mobile.test_case.utils.mappers.TaskMapper;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryDto;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoShortRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {

    private static final String TEXT_MESSAGE = "Via UserService task was not found";

    private static final String TEXT = "No task to sent!";

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Override
    public List<TaskCreationDtoResponse> getListOfAllTasksCreatedByAuthor(Long authorId, Integer from, Integer size) {

        log.info("%nVia UserTaskService Tasks List was sent through TaskRepo by User with id %d at time:"
                .formatted(authorId) + LocalDateTime.now() + "\n");

        return taskRepository.findAllByAuthor_Id(authorId, this.pageRequestCalculator(from, size))
                .stream()
                .filter(t-> !t.getIsDeleted())
                .map(TaskMapper::toTaskCreationDtoResponse)
                .toList();
    }

    @Override
    public TaskDtoResponse getTaskCreatedByAuthor(Long authorId, Long taskId) {

        Task taskFromDb = this.checkTaskByIdAndUserId(taskId, authorId);

        List<CommentaryDto> list = taskFromDb.getComments()
                .stream()
                .filter(c->!c.getIsDeleted())
                .map(CommentaryMappers::toCommentaryDto)
                .toList();

        log.info("%nVia UserTaskService Task was sent through TaskRepo by User with id %d at time:"
                .formatted(authorId) + LocalDateTime.now() + "\n");
        return TaskMapper.toTaskDtoResponse(taskFromDb, list);
    }

    @Override
    public TaskCreationDtoResponse createTaskByAuthor(TaskCreationRequest createTask) {

        User author = this.checkUserInByEmail(createTask.authorEmail());

        User assignee = this.checkUserInByEmail(createTask.assigneeEmail());

        log.info("%nVia UserTaskService Task was updated post by User with email %s in to task %s at time:"
                .formatted(createTask.authorEmail(), createTask) + LocalDateTime.now() + "\n");

        return TaskMapper.toTaskCreationDtoResponse(taskRepository.saveAndFlush(TaskMapper.toTask(createTask,
                                                                                                  author,
                                                                                                  assignee)));
    }

    @Override
    public TaskUpdatedDtoResponse updateTaskByAuthor(Long taskId, TaskUpdatedDtoShortRequest updateTask) {

        Task taskFromDb = this.checkTaskById(taskId);
        User author = this.checkUserInByEmail(updateTask.authorEmail());
        User assignee = this.checkUserInByEmail(taskFromDb.getAssignee().getEmail());

        if (!author.getId().equals(assignee.getId())) {
            log.info("User not Assignee");
            throw new BadRequestException("User not Assignee");
        }

        log.info("%nVia UserTaskService task %s was updated".formatted(updateTask));

        taskFromDb = taskRepository.saveAndFlush(this.updateTask(taskFromDb, updateTask));

        return TaskMapper.toTaskUpdatedDtoResponse(taskFromDb, assignee);
    }

    @Override
    public TaskCreationDtoResponse deleteTaskByAuthor(Long authorId, Long taskId) {

        Task taskFromDb = this.checkTaskById(taskId);
        taskFromDb.setIsDeleted(true);

        log.info("%nVia UserTaskService task %s was deleted".formatted(taskFromDb));

        return TaskMapper.toTaskCreationDtoResponse(taskRepository.saveAndFlush(taskFromDb));
    }

    @Override
    public List<TaskCreationDtoResponse> getListOfAllTasksAssignedToUser(Long assigneeId, Integer from, Integer size) {

        log.info("%nVia UserTaskService Tasks Assigned to User List was send through TaskRepo by User with id %d at time:"
                .formatted(assigneeId) + LocalDateTime.now() + "\n");

        return taskRepository.findAllByAssignee_Id(assigneeId, this.pageRequestCalculator(from, size))
                .stream()
                .filter(t-> !t.getIsDeleted())
                .map(TaskMapper::toTaskCreationDtoResponse)
                .toList();
    }

    private User checkUserInByEmail(String userEmail) {

        log.info("%nVia UserTaskService userEmail %s was found".formatted(userEmail));

        return userRepository.getUserByMail(userEmail).orElseThrow(() -> {

            log.info("%nVia UserService userEmail %s was not found".formatted(userEmail));
            return new ObjectNotFoundException("Via UserService user was not found");
        });
    }

    private Task checkTaskById(Long taskId) {

        Task taskFromDb = taskRepository.findTaskById(taskId).orElseThrow(() -> {

            log.info("%nVia UserTaskService task %d was not found".formatted(taskId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });

        if (Boolean.TRUE.equals(taskFromDb.getIsDeleted())) {
            log.info("%nVia UserTaskService Task requested was deleted at time:"
                    + LocalDateTime.now() + "\n");
            throw new ObjectNotFoundException(TEXT);
        }

        log.info("%nVia UserService task %d was found".formatted(taskId));

        return taskFromDb;
    }

    private Task checkTaskByIdAndUserId(Long taskId, Long authorId) {

        log.info("%nVia UserService task %d was found".formatted(taskId));
        return taskRepository.findTaskByIdAndAndAuthor(taskId, authorId).orElseThrow(() -> {

            log.info("%nVia UserTaskService task %d was not found".formatted(taskId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });
    }

    private Task updateTask(Task task,
                            TaskUpdatedDtoShortRequest updateTask) {

        if (updateTask.newStatus() != null) {
            task.setTaskStatus(updateTask.newStatus());
        }

        log.info("%nVia UserTaskService task %s was updated from updateTask %s".formatted(task, updateTask));
        return task;
    }

    private PageRequest pageRequestCalculator(Integer from,
                                              Integer size) {

        return PageRequest.of(from / size, size);
    }
}
