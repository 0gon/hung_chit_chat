package chat.jwtservice.jwt.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestRefreshTokenDto {

    private String refreshToken;

    public RequestRefreshTokenDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
