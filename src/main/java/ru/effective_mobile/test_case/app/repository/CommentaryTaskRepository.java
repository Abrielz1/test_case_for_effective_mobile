package ru.effective_mobile.test_case.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.test_case.app.entity.Commentary;
import java.util.Optional;

@Repository
public interface CommentaryTaskRepository extends JpaRepository<Commentary, Long> {

    Optional<Commentary> findCommentaryByTask_IdAndId(Long taskId, Long commentaryId);
}
