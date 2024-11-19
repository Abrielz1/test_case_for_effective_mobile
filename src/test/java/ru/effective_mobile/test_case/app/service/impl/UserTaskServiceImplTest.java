package ru.effective_mobile.test_case.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import ru.effective_mobile.test_case.app.entity.Commentary;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import ru.effective_mobile.test_case.app.repository.TaskRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoShortRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTaskServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private UserTaskServiceImpl userTaskService;

    private TaskCreationDtoResponse taskCreationDtoResponse;

    private TaskDtoResponse taskDtoResponse;

    private Task task1;

    private Task task2;

    private Task task3;

    private User user;

    private User admin;

    private Commentary commentary1;

    private Commentary commentary2;

    private Commentary commentary3;

    TaskCreationRequest taskCreationRequest;

    TaskUpdatedDtoShortRequest taskUpdatedDtoShortRequest;

    TaskUpdatedDtoResponse taskUpdatedDtoResponse;

    @BeforeEach
    void setUp() {

        List<Commentary> list = new ArrayList<>();
        list.add(commentary1);
        list.add(commentary2);

        user = User
                .builder()
                .id(2L)
                .email("user@mail.com")
                .password("123")
                .isBanned(false)
                .isDeleted(false)
                .tasksCreatedByUser(null)
                .tasksAssignedToUser(null)
                .commentaryList(null)
                .roles(new HashSet<>(RoleType.ROLE_USER.ordinal()))
                .build();

        admin = User
                .builder()
                .id(1L)
                .email("admin@mail.com")
                .password("123")
                .isBanned(false)
                .isDeleted(false)
                .roles(new HashSet<>(RoleType.ROLE_ADMIN.ordinal()))
                .tasksCreatedByUser(null)
                .tasksAssignedToUser(null)
                .commentaryList(null)
                .build();

        task1 = Task
                .builder()
                .id(1L)
                .taskHeader("oops1")
                .taskDescription("oooppps!1")
                .taskStatus(TaskStatus.IN_PROCESS)
                .taskPriority(Priorities.MEDIUM)
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .comments(list)
                .author(user)
                .assignee(admin)
                .build();

        task2 = Task
                .builder()
                .id(2L)
                .taskHeader("oops!2")
                .taskDescription("oooppps!2")
                .taskStatus(TaskStatus.WAITING)
                .taskPriority(Priorities.HIGH)
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .author(user)
                .assignee(admin)
                .comments(new ArrayList<>())
                .build();

        task3 = Task
                .builder()
                .id(3L)
                .taskHeader("oops!3")
                .taskDescription("oooppps!3")
                .taskStatus(TaskStatus.COMPLETED)
                .taskPriority(Priorities.HIGH)
                .creationDate(LocalDateTime.now())
                .comments(new ArrayList<>())
                .isDeleted(true)
                .author(user)
                .assignee(user)
                .build();

        commentary1 = Commentary
                      .builder()
                      .id(1L)
                      .commentaryHeader("comm1")
                      .commentaryText("text1")
                      .creationDate(LocalDateTime.now())
                      .isDeleted(false)
                      .user(user)
                      .task(task1)
                      .build();

        commentary2 = Commentary
                      .builder()
                      .id(2L)
                      .commentaryHeader("comm2")
                      .commentaryText("text2")
                      .creationDate(LocalDateTime.now())
                      .isDeleted(false)
                      .user(admin)
                      .task(task1)
                      .build();

        commentary3 = Commentary
                      .builder()
                      .id(3L)
                      .commentaryHeader("comm3")
                      .commentaryText("opps!1")
                      .creationDate(LocalDateTime.now())
                      .isDeleted(true)
                      .user(admin)
                      .task(task1)
                      .build();
    }

    @Test
    @DisplayName("getListOfAllTasksCreatedByAuthor")
    void When_getListOfAllTasksCreatedByAuthor_ThenGetListOf2TaskBecause1SetIsDeletedToTrue() {

        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        PageRequest p = PageRequest.of(from, size);
        when(taskRepository.findAllByAuthor_Id(1L, p)).thenReturn(tasks);

        List<TaskCreationDtoResponse> list = userTaskService.getListOfAllTasksCreatedByAuthor(1L, from, size);
        assertEquals(2L, list.size(), "value: 2");
        assertEquals(task1.getTaskDescription(), list.get(0).description(), "value: oooppps!1");
        assertEquals(task2.getTaskDescription(), tasks.get(1).getTaskDescription(), "value: oooppps!2");
    }

    @Test
    @DisplayName("getTaskCreatedByAuthor")
    void When_getTaskCreatedByAuthor_ThenGetTaskCreatedByAuthor() {

        List<Commentary> list = new ArrayList<>();
        list.add(commentary1);
        list.add(commentary2);

        when(taskRepository.findTaskByIdAndAndAuthor(1L, 2L)).thenReturn(Optional.ofNullable(task1));
        task1.setComments(list);

        taskDtoResponse = userTaskService.getTaskCreatedByAuthor(2L, 1L);

        assertEquals(task1.getComments().size(), taskDtoResponse.list().size(), "value: Must be -> 2");
        assertEquals(task1.getTaskHeader(), taskDtoResponse.header(), "oops!1");
    }

    @Test
    @DisplayName("createTaskByAuthor")
    void Then_createTaskByAuthor_WhenTaskWithHeaderOoops1Getted() {

        taskCreationRequest = new TaskCreationRequest("1", "1", TaskStatus.IN_PROCESS, Priorities.LOW, user.getEmail(), admin.getEmail());

        when(userRepository.getUserByMail(anyString())).thenReturn(Optional.ofNullable(user));
        when(userRepository.getUserByMail(anyString())).thenReturn(Optional.ofNullable(admin));
        when(taskRepository.saveAndFlush(any(Task.class))).thenReturn(task1);

        TaskCreationDtoResponse taskCreationDtoResponse = userTaskService.createTaskByAuthor(taskCreationRequest);

        assertEquals(taskCreationDtoResponse.description(), task1.getTaskDescription(), "value: oops1");
    }

    @Test
    @DisplayName("createTaskByAuthor")
    void Then_updateTaskByAuthor_WhenWhenTaskWithPriorityStatusMEDIUMGetted() {

        taskUpdatedDtoShortRequest = new TaskUpdatedDtoShortRequest(TaskStatus.COMPLETED, user.getEmail());

        when(taskRepository.findTaskById(anyLong())).thenReturn(Optional.ofNullable(task1));
        when(userRepository.getUserByMail(anyString())).thenReturn(Optional.ofNullable(user));
        when(userRepository.getUserByMail(anyString())).thenReturn(Optional.ofNullable(admin));
        when(taskRepository.saveAndFlush(any(Task.class))).thenReturn(task1);

        taskUpdatedDtoResponse = userTaskService.updateTaskByAuthor(2L, taskUpdatedDtoShortRequest);

        assertEquals(taskUpdatedDtoResponse.priorities(), task1.getTaskPriority(), "value: MEDIUM");
    }

    @Test
    @DisplayName("deleteTaskByAuthor")
    void Then_deleteTaskByAuthor_WhenTaskWith_oops1Getted() {

        when(taskRepository.findTaskById(anyLong())).thenReturn(Optional.ofNullable(task1));
        when(taskRepository.saveAndFlush(any(Task.class))).thenReturn(task1);
        taskCreationDtoResponse = userTaskService.deleteTaskByAuthor(2L, 1L);

        assertEquals(taskCreationDtoResponse.header(), task1.getTaskHeader(), "value: oops1");
    }

    @Test
    @DisplayName("getListOfAllTasksAssignedToUser")
    void Then_getListOfAllTasksAssignedToUser_WhenTaskListWith_2ItemsGetted() {
        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        PageRequest p = PageRequest.of(from, size);
        when(taskRepository.findAllByAssignee_Id(1L, p)).thenReturn(tasks);
        List<TaskCreationDtoResponse> list = userTaskService.getListOfAllTasksAssignedToUser(1L, from, size);

        assertEquals(2L, list.size(), "value: 2");
        assertEquals(task1.getTaskDescription(), list.get(0).description(), "value: oooppps!1");
        assertEquals(task2.getTaskDescription(), tasks.get(1).getTaskDescription(), "value: oooppps!2");
    }
}