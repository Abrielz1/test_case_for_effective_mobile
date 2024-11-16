package ru.effective_mobile.test_case.utils.mappers;

import lombok.extern.slf4j.Slf4j;
import ru.effective_mobile.test_case.exception.exceptions.UnsupportedStateException;

@Slf4j
public class CommentaryMappers {

    private CommentaryMappers() {

        log.info("Was attempt object creation for utility class %s"
                .formatted(CommentaryMappers.class.getName()));
        throw new UnsupportedStateException("Utility Class!");
    }

    // todo mapper logic awaits

}
