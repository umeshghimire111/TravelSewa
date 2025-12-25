package transportation.travelsewa.bookingOtp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;


    @PostMapping("/re-send")
    public ResponseEntity<String> resendOtp(@RequestBody OtpRequest request) {
        String response = otpService.sendOtp(request.getBookingId(), request.getEmail());
        return ResponseEntity.ok(response);
    }


}
