package transportation.travelsewa.forgetPassword;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportation.travelsewa.forgetPassword.dto.ForgetPasswordDto;
import transportation.travelsewa.forgetPassword.dto.ResetPasswordRequest;



@RestController
@RequestMapping("/api/forget")
@RequiredArgsConstructor
public class ForgetPasswordController {

    private final EmailService emailService;

    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestBody @Valid ForgetPasswordDto request) {
        return ResponseEntity.ok(emailService.sendOtp(request.getEmail()));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody  @Valid ResetPasswordRequest request){
        return ResponseEntity.ok(emailService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword()));
    }
}

