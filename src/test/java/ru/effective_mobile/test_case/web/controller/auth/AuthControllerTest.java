package ru.effective_mobile.test_case.web.controller.auth;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.effective_mobile.test_case.security.repository.SecurityRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityRepository securityRepository;

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

        mockMvc.perform(request)
                .andExpectAll(status().isCreated(),
                        content().contentType("application/json")
                );

        assertEquals(1, securityRepository.findAll().size(), "value: size Must Be 1");
        var person = securityRepository.findByEmail("value: email@mail.com");
        assertNotNull(person.get(), "value: Must not Be NULL");
        assertEquals("email@mail.com", person.get().getEmail(), "value: Must be email@mail.com");
        assertNotEquals("password", person.get().getPassword(), "value: Must be password");
        assertNotEquals(0, person.get().getId(), "value: Must NOT be 0");
    }
}