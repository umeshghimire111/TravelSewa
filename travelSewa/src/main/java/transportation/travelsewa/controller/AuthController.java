package transportation.travelsewa.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportation.travelsewa.dto.LoginRequest;
import transportation.travelsewa.dto.RequestResponse;
import transportation.travelsewa.dto.SignupRequest;
import transportation.travelsewa.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<RequestResponse> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<RequestResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<RequestResponse> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}
