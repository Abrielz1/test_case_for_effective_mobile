package ru.effective_mobile.test_case.utils.mappers;

import lombok.extern.slf4j.Slf4j;
import ru.effective_mobile.test_case.app.entity.Commentary;
import ru.effective_mobile.test_case.app.entity.Task;
import ru.effective_mobile.test_case.app.entity.User;
import ru.effective_mobile.test_case.utils.exception.exceptions.UnsupportedStateException;
import ru.effective_mobile.test_case.web.dto.request.post.CommentaryCreationRequest;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryFullUpdateResponseDto;
import ru.effective_mobile.test_case.web.dto.responce.post.CommentaryShortUpdateResponseDto;
import java.time.LocalDateTime;

@Slf4j
public class CommentaryMappers {

    private CommentaryMappers() {

        log.info("Was attempt object creation for utility class %s"
                .formatted(CommentaryMappers.class.getName()));
        throw new UnsupportedStateException("Utility Class!");
    }

    public static Commentary toCommentary(CommentaryCreationRequest newCommentary, Task taskToComment, User commentator) {

        new Commentary();
        return Commentary
                .builder()
                .commentaryHeader(newCommentary.commentaryHeader())
                .commentaryText(newCommentary.commentaryText())
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .user(commentator)
                .task(taskToComment)
                .build();
    }

    public static CommentaryFullUpdateResponseDto toCommentaryFullUpdateDtoResponse(Commentary commentary) {

        return new CommentaryFullUpdateResponseDto(commentary.getId(),
                                                   commentary.getCommentaryHeader(),
                                                   commentary.getCommentaryText(),
                                                   commentary.getUser().getEmail(),
                                                   commentary.getCreationDate());
    }

    public static CommentaryShortUpdateResponseDto toCommentaryShortUpdateResponseDto(Commentary commentary) {

        return new CommentaryShortUpdateResponseDto(commentary.getCommentaryHeader(),
                                                    commentary.getCommentaryText(),
                                                    commentary.getUser().getEmail(),
                                                    commentary.getCreationDate());
    }

    public static CommentaryDto toCommentaryDto(Commentary commentary) {

        return new CommentaryDto(commentary.getCommentaryHeader(),
                                 commentary.getCommentaryText(),
                                 commentary.getUser().getEmail(),
                                 commentary.getCreationDate());
    }
}