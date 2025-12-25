package transportation.travelsewa.bookingOtp;


import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Table(name = "booked_otp")
@Data
public class BookingOtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String otp;
    private LocalDateTime expiryTime;
}