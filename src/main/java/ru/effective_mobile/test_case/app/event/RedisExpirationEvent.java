package ru.effective_mobile.test_case.app.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;
import ru.effective_mobile.test_case.app.model.RefreshToken;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;

@Slf4j
@Component
public class RedisExpirationEvent {

    @EventListener
    public void handleRedisKeyExpiredEvent(RedisKeyExpiredEvent<RefreshToken> event) {

        RefreshToken  expiredRefreshToken = (RefreshToken) event.getValue();

        if (expiredRefreshToken == null) {
            throw new ObjectNotFoundException("Refresh token is null in handleRedisKeyExpiredEvent function");
        }

        log.info("Refresh token with key: {}" +
                        " has expired and Refresh Token is: {}",
                expiredRefreshToken.getId(), expiredRefreshToken.getToken());
    }
}
