package transportation.travelsewa.bookingOtp;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<BookingOtpEntity, Long> {

    Optional<BookingOtpEntity> findByBookingId(Long bookingId);
}