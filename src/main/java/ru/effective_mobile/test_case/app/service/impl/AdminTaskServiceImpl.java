package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.repository.TaskRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.AdminTaskService;
import ru.effective_mobile.test_case.utils.exception.exceptions.BadRequestException;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;
import ru.effective_mobile.test_case.utils.mappers.TaskMapper;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminTaskServiceImpl implements AdminTaskService {

    private static final String TEXT_MESSAGE = "Via AdminService task was not found";

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(Integer from, Integer size) {

        log.info("%nVia AdminTaskService Tasks List with deleted tasks was sent through TaskRepo by Admin at time:"
                + LocalDateTime.now() + "\n");

        return taskRepository.findAll(this.pageRequestCalculator(from, size))
                .stream()
                .map(TaskMapper::toTaskUpdatedFullDtoResponse)
                .toList();
    }

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOn(Integer from, Integer size) {

        log.info("%nVia AdminTaskService Tasks List with deleted tasks only was sent through TaskRepo by Admin at time:"
                 + LocalDateTime.now() + "\n");

        return taskRepository.findAll(this.pageRequestCalculator(from, size))
                .stream()
                .filter(Task::getIsDeleted)
                .map(TaskMapper::toTaskUpdatedFullDtoResponse)
                .toList();
    }

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdminWithIsDeletedOff(Integer from, Integer size) {

        log.info("%nVia AdminTaskService Tasks List with only deleted tasks was sent through TaskRepo by Admin at time:"
                + LocalDateTime.now() + "\n");

        return taskRepository.findAll(this.pageRequestCalculator(from, size))
                .stream()
                .filter(t -> !t.getIsDeleted())
                .map(TaskMapper::toTaskUpdatedFullDtoResponse)
                .toList();
    }

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdmin(Long authorId, Integer from, Integer size) {

        log.info("%nVia AdminTaskService Tasks List was sent through TaskRepo by Admin at time:"
                + LocalDateTime.now() + "\n");

        return taskRepository.findAllByAuthor_Id(authorId, this.pageRequestCalculator(from, size))
                .stream()
                .map(TaskMapper::toTaskUpdatedFullDtoResponse)
                .toList();
    }

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdmin(Long assigneeId, Integer from, Integer size) {

        log.info("%nVia AdminTaskService Tasks List was sent through TaskRepo by Admin at time:"
                + LocalDateTime.now() + "\n");

        return taskRepository.findAllByAssignee_Id(assigneeId, this.pageRequestCalculator(from, size))
                .stream()
                .map(TaskMapper::toTaskUpdatedFullDtoResponse)
                .toList();
    }

    @Override
    public TaskUpdatedFullDtoResponse getTaskByAuthorIdByAdmin(Long authorId) {

        log.info("%nVia AdminTaskService Task created by author with" +
                " authorId: %d was sent through TaskRepo by Admin at time:".formatted(authorId)
                + LocalDateTime.now() + "\n");
        return TaskMapper.toTaskUpdatedFullDtoResponse(this.checkTaskByAuthorId(authorId));
    }

    @Override
    public TaskUpdatedFullDtoResponse getTaskByAssigneeIdByAdmin(Long assigneeId) {

        log.info("%nVia AdminTaskService Task created by assignee with" +
                " assigneeId: %d was sent through TaskRepo by Admin at time:".formatted(assigneeId)
                + LocalDateTime.now() + "\n");
        return TaskMapper.toTaskUpdatedFullDtoResponse(this.checkTaskByAssignedId(assigneeId));
    }

    @Override
    public TaskCreationDtoResponse createTaskByAdmin(TaskCreationRequest newTask) {

        User assignee = this.checkUserInByEmail(newTask.author());

        User author = this.checkUserInByEmail(newTask.assignee());

        log.info(("%nVia AdminTaskService Task was updated post by User with authorId %d" +
                " and assignee with assigneeId : %d in to task %s at time:")
                .formatted(author.getId(), assignee.getId(), newTask) + LocalDateTime.now() + "\n");

        return TaskMapper.toTaskCreationDtoResponse(taskRepository.saveAndFlush(TaskMapper.toTask(newTask,
                author,
                assignee)));
    }

    @Override
    public TaskUpdatedFullDtoResponse updateTaskByAdmin(Long authorId, Long taskId, TaskUpdatedDtoRequest updateTask) {

        log.info(("%nVia AdminTaskService Task was updated post by User with authorId %d" +
                " and assignee with taskId : %d in to task at time:")
                .formatted(authorId,  taskId) + LocalDateTime.now() + "\n");
        return TaskMapper.toTaskUpdatedFullDtoResponse(
                taskRepository.saveAndFlush(this.updateTask(authorId, taskId, updateTask)));
    }

    @Override
    public TaskCreationDtoResponse deleteTaskByAdmin(Long authorId, Long taskId) {
        Task taskFromDb = this.checkTaskByAuthorId(authorId);
        taskFromDb.setIsDeleted(true);

        log.info("%nVia AdminTaskService task %s was deleted".formatted(taskFromDb));

        return TaskMapper.toTaskCreationDtoResponse(taskRepository.saveAndFlush(taskFromDb));
    }

    private PageRequest pageRequestCalculator(Integer from,
                                              Integer size) {

        return PageRequest.of(from / size, size);
    }

    private Task checkTaskByAuthorId(Long authorId) {

        log.info("%nVia AdminTaskService task with authorId %d was found".formatted(authorId));

        return taskRepository.findTaskByAuthor_Id(authorId).orElseThrow(() -> {

            log.info("%nVia UserService task with authorId %d  was not found".formatted(authorId));
            return new ObjectNotFoundException("TEXT_MESSAGE");
        });
    }

    private Task checkTaskByAssignedId(Long assigneeId) {

        log.info("%nVia AdminTaskService task with authorId %d was found".formatted(assigneeId));

        return taskRepository.findTaskByAssignee_Id(assigneeId).orElseThrow(() -> {

            log.info("%nVia UserService task with assigneeId: %d was not found".formatted(assigneeId));
            return new ObjectNotFoundException(TEXT_MESSAGE);
        });
    }

    private User checkUserInByEmail(String userEmail) {

        log.info("%nVia AdminTaskService userEmail %s was found".formatted(userEmail));

        return userRepository.findByEmail(userEmail).orElseThrow(() -> {

            log.info("%nVia UserService userEmail %s was not found".formatted(userEmail));
            return new ObjectNotFoundException("Via UserService user was not found");
        });
    }

    private Task checkTaskByTaskId(Long taskId) {

        log.info("%nVia AdminTaskService task with taskId %d was found".formatted(taskId));
        return taskRepository.findTaskById(taskId).orElseThrow(() -> {

            log.info("%nVia AdminTaskService taskId %d was not found".formatted(taskId));
            return new ObjectNotFoundException("Via UserService task was not found");
        });
    }

    private Task updateTask(Long authorId,
                            Long taskId,
                            TaskUpdatedDtoRequest updateTask) {

        Task task = this.checkTaskByTaskId(taskId);

        if (!task.getAuthor().getId().equals(authorId)) {
            throw new BadRequestException("Task not own by user!");
        }

        if (updateTask.updateStatus() != null) {
            task.setTaskStatus(updateTask.updateStatus());
        }

        if (updateTask.priorityStatus() != null) {
            task.setTaskPriority(updateTask.priorityStatus());
        }

        if (updateTask.assigneeEmail() != null) {
            User assignee = this.checkUserInByEmail(updateTask.assigneeEmail());
            task.setAssignee(assignee);
        }

        log.info("%nVia AdminTaskService task %s was updated from updateTask %s".formatted(task, updateTask));
        return task;
    }
}