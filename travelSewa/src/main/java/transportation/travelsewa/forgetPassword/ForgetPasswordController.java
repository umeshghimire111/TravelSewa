package transportation.travelsewa.forgetPassword;

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

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody ForgetPasswordDto request) {
        return ResponseEntity.ok(emailService.sendOtp(request.getEmail()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request){
        return ResponseEntity.ok(emailService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword()));
    }
}



