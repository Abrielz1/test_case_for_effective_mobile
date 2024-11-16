package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.repository.AdminRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;
import ru.effective_mobile.test_case.app.service.AdminService;
import ru.effective_mobile.test_case.web.dto.request.account.UpdateUserAccountRequestDto;
import ru.effective_mobile.test_case.web.dto.request.task.TaskCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.account.UserResponseFullDto;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskCreationDtoResponse;
import ru.effective_mobile.test_case.web.dto.request.task.TaskUpdatedDtoRequest;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedDtoResponse;
import ru.effective_mobile.test_case.web.dto.responce.task.TaskUpdatedFullDtoResponse;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    /*
  todo:
   API должно позволять получать задачи конкретного автора или исполнителя,
   а также все комментарии к ним.
   Необходимо обеспечить фильтрацию и пагинацию вывода.
    */

    // todo:
    //  Сервис должен корректно обрабатывать ошибки и возвращать понятные сообщения,
    //  а также валидировать входящие данные.

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAdmin(Integer from, Integer size) {
        return null;
    }

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAuthorIdByAdmin(Long authorId, Integer from, Integer size) {
        return null;
    }

    @Override
    public List<TaskUpdatedFullDtoResponse> getAllTasksListByAssigneeIdByAdmin(Long assigneeId, Integer from, Integer size) {
        return null;
    }

    @Override
    public TaskUpdatedFullDtoResponse getTaskByAuthorIdByAdmin(Long authorId) {
        return null;
    }

    @Override
    public TaskUpdatedFullDtoResponse getTaskByAssigneeIdByAdmin(Long assigneeId) {
        return null;
    }

    @Override
    public TaskCreationDtoResponse createTaskByAdmin(TaskCreationRequest newTask) {
        return null;
    }

    @Override
    public TaskUpdatedDtoResponse updateTaskByAdmin(Long adminId, Long taskId, TaskUpdatedDtoRequest updateTask) {
        return null;
    }

    @Override
    public TaskCreationDtoResponse deleteTaskByAdmin(Long adminId, Long taskId) {
        return null;
    }

    @Override
    public UserResponseFullDto editUserAccountByAdmin(Long userId, UpdateUserAccountRequestDto updateAccount) {
        return null;
    }
}
