package site.aviralgupta.video_rental_system.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.aviralgupta.video_rental_system.dto.UserLoginDto;
import site.aviralgupta.video_rental_system.dto.UserRegisterDto;
import site.aviralgupta.video_rental_system.service.AuthService;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRegisterDto userRegisterDto){

        authService.signup(userRegisterDto);
        return ResponseEntity.ok(Map.of("message", "success"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto dto){

        String jwt = authService.verify(dto);

        if(jwt == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Unauthorized"));

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}
