package transportation.travelsewa.qrScanner;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@Controller
public class QRController {

    private final QRGeneratorService qrGenerator;
    @GetMapping("/{bookingId}")
    public ResponseEntity<String> getBookingQR(@PathVariable Long bookingId) {

        return ResponseEntity.ok(qrGenerator.generateBookingQR(bookingId));
    }
}
