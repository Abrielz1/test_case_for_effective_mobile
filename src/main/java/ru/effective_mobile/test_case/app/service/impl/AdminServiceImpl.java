package ru.effective_mobile.test_case.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.app.repository.AdminRepository;
import ru.effective_mobile.test_case.app.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

}
