package transportation.travelsewa.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportation.travelsewa.bookingOtp.OtpRequest;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody BookingDto request) {
        String response = bookingService.bookTicket(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyBooking(@RequestBody OtpRequest request) {
        String response = bookingService.verifyOtpAndConfirm(request.getBookingId(), request.getOtp());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
