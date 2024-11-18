package ru.effective_mobile.test_case.web.controller.auth;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.effective_mobile.test_case.app.model.enums.RoleType;
import ru.effective_mobile.test_case.security.jwt.service.SecurityService;
import ru.effective_mobile.test_case.security.repository.SecurityRepository;
import ru.effective_mobile.test_case.utils.mappers.UserMapper;
import ru.effective_mobile.test_case.web.dto.request.account.CreateAccountRequest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    @Transactional
    @DisplayName(value = "Регистрация с уникальными данными: " +
            " код 201, формат данных JSON, запись добавлена в БД")
    void Then_register_EmailIsUnique_When_returns201() throws Exception {
        //Then given ->
        var request = MockMvcRequestBuilders.post("/auth/register")
                .contentType("application/json")
                .content("""
                        {
                        "email" : "email@mail.com",
                        "password" : "password",
                        "roles": [
                                "ROLE_ADMIN"
                            ]
                        }
                        """);
        // -> When getting answer ->
        mockMvc.perform(request)
                // -> then perform ->
                .andExpectAll(status().isCreated(),
                        content().contentType("application/json")
                );
                    // and then get ->

        assertEquals(1, securityRepository.findAll().size(), "size Must Be 1");
        var person = securityRepository.findByEmail("email@mail.com");
        assertNotNull(person.get(), "Must not Be NULL");
        assertEquals("email@mail.com", person.get().getEmail(), "Must be email@mail.com");
        assertNotEquals("password", person.get().getPassword(), "Must be password");
        assertNotEquals(0, person.get().getId(), "Must NOT be 0");
    }

    @Test
    @Transactional
    @DisplayName(value = "Аутентификации с существующими данными: 200 с JWT токеном")
    void Then_loginIntoAccount_When_returns200WithJWToken() throws Exception {
        //given
        List<RoleType> list = new ArrayList<>();
        list.add(RoleType.ROLE_ADMIN);

        var user = new CreateAccountRequest("email", "password", list);
        var user2 = UserMapper.toUser(user, passwordEncoder);
        //securityService.registerUserAccount(user);
        securityRepository.saveAndFlush(user2);

        var request = MockMvcRequestBuilders.post("/auth/login")
                .contentType("application/json")
                .content("""
                        {
                        "email" : "email@mail.com",
                        "password" : "password",
                           "roles": [
                                "ROLE_ADMIN"
                            ]
                        }
                        """);
        //when
        mockMvc.perform(request)
                //then
                .andExpectAll(status().isOk(),
                        content().contentType("application/json"),
                        jsonPath("$.token").exists()
                );
    }

    @Test
    void logoutOfCurrentAccount() {
    }

    @Test
    void refreshTokenRefresh() {
    }
}