package ru.effective_mobile.test_case.app.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.test_case.app.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByAuthor_Id(Long authorId, PageRequest page);

    List<Task> findAllByAssignee_Id(Long assigneeId, PageRequest page);

    Optional<Task> findTaskById(Long id);

    Optional<Task> findTaskByAuthor_Id(Long authorId);

    Optional<Task> findTaskByAssignee_Id(Long assigneeId);

    @Query(value = """
                         SELECT * FROM tasks
                         WHERE tasks.author_id = : authorId
                         """, nativeQuery = true)
    List<Task> getAllTaskByAuthor(@Param("authorId") Long authorId,
                                  @Param("page") PageRequest page);

    @Query(value = """
                   SELECT *
                   FROM tasks
                   WHERE tasks.author_id =  : authorId
                   """, nativeQuery = true)
    Optional<Task> getTaskCreatedByAuthor(@Param("authorId") Long authorId);

    @Query(value = """
                   SELECT *
                   FROM tasks
                   WHERE tasks.assigne_id = :assigneeId
                   """, nativeQuery = true)
    List<Task> getListOfAllTasksAssignedToUser(@Param("assigneeId")Long assigneeId, PageRequest page);
}
