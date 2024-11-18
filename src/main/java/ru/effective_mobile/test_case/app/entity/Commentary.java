package ru.effective_mobile.test_case.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс описывающий сущность комментарий (Commentary) к сущности задача (Task) сущностью пользователь (User)
 **/

@Table(name = "commentaries")
@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Commentary implements Serializable {

    /**
     * id комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
      * заголовок комментария
     */
    @Column(name = "commentary_header", columnDefinition = "VARCHAR(320)")
    private String commentaryHeader;

    /**
     * текст комментария
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonProperty("commentaryText")
    private String commentaryText;

    /**
     * дата создания комментария
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    @JsonProperty("creationDate")
    private LocalDateTime creationDate;

    /**
     * флаг удалён/deleted ли комментарий
     */
    @Column(name = "is_deleted")
    @JsonProperty("isDeleted")
    private Boolean isDeleted;

    /**
     * автор
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    /**
     * задача
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;
}