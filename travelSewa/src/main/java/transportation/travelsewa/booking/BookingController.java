package transportation.travelsewa.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/travels")
@RequiredArgsConstructor

public class BookingController {


    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<Booking>bookbus(@RequestBody BookingDto booking){
        return ResponseEntity.ok( bookingService.createBooking(booking));
    }

    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping("/booking/user/{userId}")
    public ResponseEntity<List<Booking>>booking(@PathVariable Long userId ){
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }
}
