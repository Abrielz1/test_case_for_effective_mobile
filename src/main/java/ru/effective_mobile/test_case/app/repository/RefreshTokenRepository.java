package ru.effective_mobile.test_case.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.test_case.app.model.RefreshToken;
import java.util.Optional;

//@Repository  extends CrudRepository<RefreshToken, Long>
public interface RefreshTokenRepository {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Long userId);
}
