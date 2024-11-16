package ru.effective_mobile.test_case.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.test_case.app.entity.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

}
