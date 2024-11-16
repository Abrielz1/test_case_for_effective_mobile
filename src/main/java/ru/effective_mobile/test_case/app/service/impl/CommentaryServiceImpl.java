package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.repository.CommentaryRepository;
import ru.effective_mobile.test_case.app.service.AdminService;
import ru.effective_mobile.test_case.app.service.CommentaryService;
import ru.effective_mobile.test_case.app.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;

    private final UserService userService;

    private final AdminService adminService;

}
