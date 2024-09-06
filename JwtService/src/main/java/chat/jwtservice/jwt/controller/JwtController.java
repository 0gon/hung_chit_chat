package chat.jwtservice.jwt.controller;

import chat.jwtservice.jwt.dto.request.RequestTokenDto;
import chat.jwtservice.jwt.dto.response.ResponseTokenDto;
import chat.jwtservice.jwt.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt-service")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/auth/v1/generate")
    public ResponseEntity<ResponseTokenDto> generatedToken(@Valid @RequestBody RequestTokenDto requestTokenDto) {

        ResponseTokenDto responseTokenDto = jwtService.generateToken(requestTokenDto);

        return ResponseEntity.ok().body(responseTokenDto);
    }

}
