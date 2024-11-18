package ru.effective_mobile.test_case.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.test_case.app.entity.User;
import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findUserById(Long userid);
}
