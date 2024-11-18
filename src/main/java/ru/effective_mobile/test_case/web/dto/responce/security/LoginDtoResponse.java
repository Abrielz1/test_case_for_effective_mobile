package ru.effective_mobile.test_case.web.dto.responce.security;

import java.util.List;

public record LoginDtoResponse(Long id,

                               String email,

                               List<String> roles,

                               String token) {
}
