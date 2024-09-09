package chat.jwtservice.jwt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestTokenDto {

    private String refreshToken;

    @NotNull
    private String memberId;

    @NotBlank
    private String email;

    @Builder
    public RequestTokenDto(String refreshToken, String memberId, String email) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
        this.email = email;
    }
}
