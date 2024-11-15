package ru.effective_mobile.test_case.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.redis.core.RedisHash;
//import org.springframework.data.redis.core.index.Indexed;
import java.time.Instant;

@Setter
@Getter
@Builder
@ToString
//@RedisHash("refresh_tokens")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

  //  @Id
  //  @Indexed
    private Long id;

 //   @Indexed
    private Long userId;

  //  @Indexed
    private String token;

 //   @Indexed
    private Instant expiryDate;
}
