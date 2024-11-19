package ru.effective_mobile.test_case.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import ru.effective_mobile.test_case.app.model.enums.Priorities;
import ru.effective_mobile.test_case.app.model.enums.TaskStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс описывающий сущность задача (Task)
 */

@Table(name = "tasks")
@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    /**
     * id задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Header task/Заголовок задачи
     * */
    @Column(name = "task_header", nullable = false)
    private String taskHeader;

    /**
     *Text commentary/Текст комментария
     * */
    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    /**
     * Task status LOW, MEDIUM, HIGH/Статус задачи LOW, MEDIUM, HIGH
     * */
    @Column(name = "task_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TaskStatus taskStatus;

    /**
     * Task status WAITING, IN_PROCESS, COMPLETED/Статус задачи WAITING, IN_PROCESS, COMPLETED
     * */
    @Column(name = "task_priority", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Priorities taskPriority;

    /**
     * дата создания задачи
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    /**
     * флаг удалён/deleted ли задача
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * автор
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    /**
     * исполнитель
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    @ToString.Exclude
    private User assignee;

    /**
     * комментарии пользователей к задаче
     */
    @OneToMany(mappedBy = "task", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Commentary> comments = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
