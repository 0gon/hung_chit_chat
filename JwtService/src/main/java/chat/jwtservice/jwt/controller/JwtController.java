package chat.jwtservice.jwt.controller;

import chat.jwtservice.jwt.dto.request.RequestLoginTokenDto;
import chat.jwtservice.jwt.dto.request.RequestRefreshTokenDto;
import chat.jwtservice.jwt.dto.response.ResponseTokenDto;
import chat.jwtservice.jwt.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDto> generatedToken(@Valid @RequestBody RequestLoginTokenDto requestLoginTokenDto) {

        ResponseTokenDto responseTokenDto = jwtService.generateToken(requestLoginTokenDto);

        return ResponseEntity.ok().body(responseTokenDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseTokenDto> generatedRefreshToken(@Valid @RequestBody RequestRefreshTokenDto requestRefreshTokenDto) {

        try {
            ResponseTokenDto responseTokenDto = jwtService.generateTokenByRefreshToken(requestRefreshTokenDto);
            return ResponseEntity.ok().body(responseTokenDto);
        } catch(IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
