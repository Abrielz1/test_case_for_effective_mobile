package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.repository.TaskRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.UserService;
import ru.effective_mobile.test_case.utils.exception.exceptions.BadRequestException;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.mappers.TaskMapper;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoShortRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String TEXT_MESSAGE = "Via UserService task was not found";

    private static final String TEXT = "No task to sent!";

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Override
    public List<TaskCreationDtoResponse> getListOfAllTasksCreatedByAuthor(Long authorId, Integer from, Integer size) {

        log.info("%nVia UserService Tasks List was sent through TaskRepo by User with id %d at time:"
                .formatted(authorId) + LocalDateTime.now() + "\n");

        return taskRepository.findAllByAuthor_Id(authorId, this.pageRequestCalculator(from, size))
                .stream()
                .filter(t-> !t.getIsDeleted())
                .map(TaskMapper::toTaskCreationDtoResponse)
                .toList();
    }

    @Override
    public TaskCreationDtoResponse getTaskCreatedByAuthor(Long authorId) {

        log.info("%nVia UserService Task was sent through TaskRepo by User with id %d at time:"
                .formatted(authorId) + LocalDateTime.now() + "\n");

        Task taskFromDb = this.checkTaskByAuthorId(authorId);



        return TaskMapper.toTaskCreationDtoResponse(taskFromDb);
    }

    @Override
    public TaskCreationDtoResponse createTaskByAuthor(Long authorId, TaskCreationRequest createTask) {

        User assignee = this.checkUserInDbById(authorId);

        User author = this.checkUserInByEmail(createTask.assignee());

        log.info("%nVia UserService Task was updated post by User with id %d in to task %s at time:"
                .formatted(authorId, createTask) + LocalDateTime.now() + "\n");

        return TaskMapper.toTaskCreationDtoResponse(taskRepository.saveAndFlush(TaskMapper.toTask(createTask,
                                                                                                  author,
                                                                                                  assignee)));
    }

    @Override
    public TaskUpdatedDtoResponse updateTaskByAuthor(Long authorId, Long taskId, TaskUpdatedDtoShortRequest updateTask) {

        Task taskFromDb = this.checkTaskById(taskId);
        User author = this.checkUserInDbById(authorId);
        User assignee = this.checkUserInByEmail(taskFromDb.getAssignee().getEmail());

        if (updateTask == null) {
            log.info("empty task to update");
            throw new BadRequestException("empty task to update");
        }

        if (!author.getId().equals(assignee.getId())) {
            log.info("User not Assignee");
            throw new BadRequestException("User not Assignee");
        }

        log.info("%nVia UserService task %s was updated".formatted(updateTask));

        return TaskMapper.toTaskUpdatedDtoResponse(
                taskRepository.saveAndFlush(this.updateTask(taskFromDb, updateTask)), assignee);
    }

    @Override
    public TaskCreationDtoResponse deleteTaskByAuthor(Long authorId, Long taskId) {

        Task taskFromDb = this.checkTaskById(taskId);
        taskFromDb.setIsDeleted(true);

        log.info("%nVia UserService task %s was deleted".formatted(taskFromDb));

        return TaskMapper.toTaskCreationDtoResponse(taskFromDb);
    }

    @Override
    public List<TaskCreationDtoResponse> getListOfAllTasksAssignedToUser(Long assigneeId, Integer from, Integer size) {

        log.info("%nVia UserService Tasks Assigned to User List was send through TaskRepo by User with id %d at time:"
                .formatted(assigneeId) + LocalDateTime.now() + "\n");

        return taskRepository.findAllByAssignee_Id(assigneeId, this.pageRequestCalculator(from, size))
                .stream()
                .filter(t-> !t.getIsDeleted())
                .map(TaskMapper::toTaskCreationDtoResponse)
                .toList();
    }

    @Override
    public TaskCreationDtoResponse getTaskAssignedToUser(Long assigneeId) {

        log.info("%nVia UserService Tasks Assigned to User List was send through TaskRepo by User with id %d at time:"
                .formatted(assigneeId) + LocalDateTime.now() + "\n");

        return TaskMapper.toTaskCreationDtoResponse(this.checkTaskByAssignedId(assigneeId));
    }

    private User checkUserInDbById(Long authorId) {

        log.info("%nVia UserService author %d was found".formatted(authorId));

        return userRepository.findById(authorId).orElseThrow(() -> {

            log.info("%nVia UserService author %d was not found".formatted(authorId));
            return new ObjectNotFoundException("Via UserService author was not found");
        });
    }

    private User checkUserInByEmail(String userEmail) {

        log.info("%nVia UserService userEmail %s was found".formatted(userEmail));

        return userRepository.findByEmail(userEmail).orElseThrow(() -> {

            log.info("%nVia UserService userEmail %s was not found".formatted(userEmail));
            return new ObjectNotFoundException("Via UserService user was not found");
        });
    }

    private Task checkTaskById(Long taskId) {

        Task taskFromDb = taskRepository.findTaskById(taskId).orElseThrow(() -> {

            log.info("%nVia UserService task %d was not found".formatted(taskId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });

        if (taskFromDb.getIsDeleted()) {
            log.info("%nVia UserService Task requested was deleted at time:"
                    + LocalDateTime.now() + "\n");
            throw new ObjectNotFoundException(TEXT);
        }

        log.info("%nVia UserService task %d was found".formatted(taskId));

        return taskFromDb;
    }

    private Task checkTaskByAuthorId(Long authorId) {


        Task taskFromDb = taskRepository.findTaskByAuthor_Id(authorId).orElseThrow(() -> {

            log.info("%nVia UserService task with authorId: %d was not found".formatted(authorId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });

        log.info("%nVia UserService task with authorId %d was found".formatted(authorId));

        if (taskFromDb.getIsDeleted()) {
            log.info("%nVia UserService Task requested by user with id: %d was deleted at time:"
                    .formatted(authorId) + LocalDateTime.now() + "\n");
            throw new ObjectNotFoundException(TEXT);
        }

        return taskFromDb;
    }

    private Task checkTaskByAssignedId(Long assigneeId) {

        Task taskFromDb = taskRepository.findTaskByAssignee_Id(assigneeId).orElseThrow(() -> {

            log.info("%nVia UserService task with assigneeId: %d was not found".formatted(assigneeId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });

        log.info("%nVia UserService task with authorId %d was found".formatted(assigneeId));

        if (taskFromDb.getIsDeleted()) {
            log.info("%nVia UserService Task requested by assignee with assigneeId: %d was deleted at time:"
                    .formatted(assigneeId) + LocalDateTime.now() + "\n");
            throw new ObjectNotFoundException(TEXT);
        }

        return taskFromDb;
    }

    private Task updateTask(Task task,
                            TaskUpdatedDtoShortRequest updateTask) {

        if (updateTask.newStatus() != null) {
            task.setTaskStatus(updateTask.newStatus());
        }

        log.info("%nVia UserService task %s was updated from updateTask %s".formatted(task, updateTask));
        return task;
    }

    private PageRequest pageRequestCalculator(Integer from,
                                              Integer size) {

        return PageRequest.of(from / size, size);
    }
}
