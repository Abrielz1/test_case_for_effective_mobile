package ru.effective_mobile.test_case.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.effective_mobile.test_case.app.entity.Commentary;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import ru.effective_mobile.test_case.app.repository.CommentaryTaskRepository;
import ru.effective_mobile.test_case.app.repository.TaskRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryShortUpdateResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminCommentaryServiceImplTest {

    @Mock
    private CommentaryTaskRepository commentaryRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCommentaryTaskServiceImpl userCommentaryTaskService;

    User commentatorAdmin1;

    User commentatorUser1;

    User commentatorUser2;

    Task taskToComment1;

    Task taskToComment2;

    Task taskToComment3;

    Commentary newComment1;

    Commentary newComment2;

    Commentary newComment3;

    Commentary newComment4;

    Commentary newComment5;

    Commentary newComment6;

    Commentary newComment7;

    CommentaryShortUpdateResponseDto commentaryShortUpdateResponseDto1;

    CommentaryShortUpdateResponseDto commentaryShortUpdateResponseDto2;

    CommentaryShortUpdateResponseDto commentaryShortUpdateResponseDto3;

    CommentaryCreationRequest newCommentary1;

    CommentaryCreationRequest newCommentary2;

    CommentaryCreationRequest newCommentary3;

    @BeforeEach
    void setUp() {

        List<Commentary> list = new ArrayList<>();
        list.add(newComment1);
        list.add(newComment2);
        list.add(newComment3);

        commentatorAdmin1 = User
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

        commentatorUser1 = User
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

        taskToComment1 = Task
                .builder()
                .id(1L)
                .taskHeader("oops1")
                .taskDescription("oooppps!1")
                .taskStatus(TaskStatus.IN_PROCESS)
                .taskPriority(Priorities.MEDIUM)
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .comments(list)
                .author(commentatorUser1)
                .assignee(commentatorAdmin1)
                .build();

        newComment1 = Commentary
                .builder()
                .id(1L)
                .commentaryHeader("comm1")
                .commentaryText("text1")
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .user(commentatorUser1)
                .task(taskToComment1)
                .build();

        newComment2 = Commentary
                .builder()
                .id(2L)
                .commentaryHeader("comm2")
                .commentaryText("text2")
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .user(commentatorAdmin1)
                .task(taskToComment1)
                .build();

        newComment3 = Commentary
                .builder()
                .id(3L)
                .commentaryHeader("comm3")
                .commentaryText("opps!1")
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .user(commentatorAdmin1)
                .task(taskToComment1)
                .build();
    }

    @Test
    void createPostByAdmin() {
    }

    @Test
    void updatePostByAdmin() {
    }

    @Test
    void deleteCommentaryByAdmin() {
    }
}