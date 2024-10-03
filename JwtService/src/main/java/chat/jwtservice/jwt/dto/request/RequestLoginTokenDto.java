package chat.jwtservice.jwt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestLoginTokenDto {

    @NotNull
    private String memberId;

    @NotBlank
    private String email;

    private String role;

    @Builder
    public RequestLoginTokenDto(String memberId, String email, String role) {
        this.memberId = memberId;
        this.email = email;
        this.role = role;
    }
}
