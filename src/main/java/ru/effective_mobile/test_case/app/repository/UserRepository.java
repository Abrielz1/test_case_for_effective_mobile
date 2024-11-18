package ru.effective_mobile.test_case.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.test_case.app.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Query(value = """
                   SELECT *
                   from users
                   WHERE users.email = :email
                   """, nativeQuery = true)
    Optional<User> getUserByMail(@Param("email") String email);
}
