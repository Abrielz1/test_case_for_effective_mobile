package ru.effective_mobile.test_case.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
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
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
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
class AdminTaskServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private AdminTaskServiceImpl adminTaskService;

    private User user;

    private User admin;

    private Commentary commentary1;

    private Commentary commentary2;

    private Task task1;

    private Task task2;

    private Task task3;

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
    }

    @Test
    @DisplayName("getAllTasksListByAdmin")
    void When_getAllTasksListByAdmin_ThenGetListOf3() {
        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        when(taskRepository.findAll(any(PageRequest.class)))
                .thenReturn((new PageImpl<>(tasks)));

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAdmin(from, size);

        assertEquals(3, list.size(), "value: 3");
        assertEquals(task1.getTaskHeader(), list.get(0).header(), "value: oops1");
    }


    @Test
    @DisplayName("getAllTasksListByAdminWithIsDeletedOff")
    void When_getAllTasksListByAdminWithIsDeletedOff_ThenGetListOf2() {
        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(taskRepository.findAll(any(PageRequest.class)))
                .thenReturn((new PageImpl<>(tasks)));

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAdminWithIsDeletedOff(from, size);

        assertEquals(2, list.size(), "value: 2");
        assertEquals(task1.getTaskHeader(), list.get(0).header(), "value: oops1");
    }

    @Test
    @DisplayName("getAllTasksListByAdminWithIsDeletedOnly")
    void When_getAllTasksListByAdminWithIsDeletedOnly_ThenGetListOf1() {
        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task3);

        when(taskRepository.findAll(any(PageRequest.class)))
                .thenReturn((new PageImpl<>(tasks)));

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAdminWithIsDeletedOnly(from, size);

        assertEquals(1, list.size(), "value: 1");
        assertEquals(task3.getTaskHeader(), list.get(0).header(), "value: oops!3");
    }

    @Test
    @DisplayName("getAllTasksListByAuthorIdByAdmin")
    void When_getAllTasksListByAuthorIdByAdminWithDeleted_Then_ThenGetListOf3() {

        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        when(taskRepository.findAllByAuthor_Id(anyLong(), any()))
                .thenReturn(tasks);

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAuthorIdByAdminWithDeleted(2L, from, size);

        assertEquals(3, list.size(), "value: 3");
        assertEquals(task1.getTaskHeader(), list.get(0).header(), "value: oops1");
    }

    @Test
    @DisplayName("getAllTasksListByAuthorIdByAdmin")
    void When_getAllTasksListByAuthorIdByAdminWithoutDeleted_Then_ThenGetListOf2() {

        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        when(taskRepository.findAllByAuthor_Id(anyLong(), any())).
                thenReturn(tasks);

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAuthorIdByAdminWithoutDeletedTasks(2L, from, size);

        assertEquals(2, list.size(), "value: 2");
        assertEquals(task1.getTaskHeader(), list.get(0).header(), "value: oops1");
    }

    @Test
    @DisplayName("getAllTasksListByAssigneeIdByAdmin")
    void When_getAllTasksListByAssigneeIdByAdminWithoutDeleted_Then_ThenGetListOf2() {
        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        when(taskRepository.findAllByAssignee_Id(anyLong(), any()))
                .thenReturn(tasks);

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAssigneeIdByAdminWithoutDeleted(1L, from, size);

        assertEquals(2, list.size(), "value: 2");
        assertEquals(task1.getTaskHeader(), list.get(0).header(), "value: oops1");
    }

    @Test
    @DisplayName("getTasksByAssigneeIdByAdmin")
    void When_getTasksByAssigneeIdByAdminWithDeleted_Then_ThenGetListOf3() {

        Integer from = 0;
        Integer size = 5;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        when(taskRepository.findAllByAssignee_Id(anyLong(), any())).thenReturn(tasks);

        List<TaskUpdatedFullDtoResponse> list = adminTaskService.getAllTasksListByAssigneeIdByAdminWithDeleted(1L, from, size);

        assertEquals(3, list.size(), "value: 3");
        assertEquals(task1.getTaskHeader(), list.get(0).header(), "value: oops1");
    }

    @Test
    @DisplayName("getTaskByTaskIdByAdmin")
    void When_getTaskByTaskIdByAdmin_ThenGetHeader() {

        List<Commentary> list = new ArrayList<>();
        list.add(commentary1);
        list.add(commentary2);

        task1.setComments(list);

        when(taskRepository.findTaskById(anyLong()))
                .thenReturn(Optional.ofNullable(task1));

        TaskDtoResponse taskDtoResponse = adminTaskService.getTaskByTaskIdByAdmin(1L);

        assertEquals(taskDtoResponse.header(), task1.getTaskHeader(), "value: oops1");
    }

    @Test
    @DisplayName("createTaskByAdmin")
    void When_createTaskByAdmin_ThenGetHeader() {

        TaskCreationRequest newTask = new TaskCreationRequest("1", "1", TaskStatus.IN_PROCESS, Priorities.LOW, user.getEmail(), admin.getEmail());

        when(userRepository.getUserByMail(anyString()))
                .thenReturn(Optional.ofNullable(user));
        when(userRepository.getUserByMail(anyString()))
                .thenReturn(Optional.ofNullable(admin));
        when(taskRepository.saveAndFlush(any(Task.class))).
                thenReturn(task1);

        TaskCreationDtoResponse taskCreationDtoResponse = adminTaskService.createTaskByAdmin(newTask);

        assertEquals(taskCreationDtoResponse.description(), task1.getTaskDescription(), "value: oops1");
    }

    @Test
    @DisplayName("updateTaskByAdmin")
    void When_updateTaskByAdmin_Then() {

        when(userRepository.getUserByMail(anyString()))
                .thenReturn(Optional.ofNullable(user));
        when(taskRepository.findTaskById(anyLong()))
                .thenReturn(Optional.ofNullable(task1));
        when(taskRepository.saveAndFlush(any(Task.class)))
                .thenReturn(task1);

        TaskUpdatedDtoRequest updateTask = new TaskUpdatedDtoRequest(TaskStatus.COMPLETED, null, user.getEmail());
        TaskUpdatedFullDtoResponse taskUpdatedFullDtoResponse = adminTaskService.updateTaskByAdmin(1L, 2L, updateTask);

        assertEquals(taskUpdatedFullDtoResponse.header(), task1.getTaskHeader(), "value: oops1");
    }

    @Test
    @DisplayName("deleteTaskByAdmin")
    void When_deleteTaskByAdmin_Then() {

        when(taskRepository.findTaskByIdAndAuthor_Id(anyLong(), anyLong()))
                .thenReturn(Optional.ofNullable(task1));
        when(taskRepository.saveAndFlush(any(Task.class)))
                .thenReturn(task1);

        TaskCreationDtoResponse taskCreationDtoResponse = adminTaskService.deleteTaskByAdmin(1L, 2L);

        assertEquals(taskCreationDtoResponse.description(), task1.getTaskDescription(), "value: oops1");
    }
}