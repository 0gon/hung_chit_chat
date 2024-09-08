package chat.jwtservice.jwt.controller;

import chat.jwtservice.jwt.dto.request.RequestTokenDto;
import chat.jwtservice.jwt.dto.response.ResponseTokenDto;
import chat.jwtservice.jwt.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/jwt-service")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDto> generatedToken(@Valid @RequestBody RequestTokenDto requestTokenDto) {

        ResponseTokenDto responseTokenDto = jwtService.generateToken(requestTokenDto);

        return ResponseEntity.ok().body(responseTokenDto);
    }

    @PostMapping("/generate")
    public ResponseEntity<ResponseTokenDto> generatedRefreshToken(@Valid @RequestBody RequestTokenDto requestTokenDto) {

        try {
            ResponseTokenDto responseTokenDto = jwtService.generateTokenByRefreshToken(requestTokenDto);
            return ResponseEntity.ok().body(responseTokenDto);
        } catch(IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
