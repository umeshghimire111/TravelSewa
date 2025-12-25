package transportation.travelsewa.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import transportation.travelsewa.bookingOtp.OtpService;
import transportation.travelsewa.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final OtpService otpService;


    public String bookTicket(BookingDto request) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();


        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();


        BookingEntity booking = new BookingEntity();
        booking.setBusId(request.getBusId());
        booking.setUserId(userId);
        booking.setSeats(request.getSeats());
        booking.setTravelDate(request.getTravelDate());
        booking.setStatus(BookingStatus.PENDING);

        bookingRepository.save(booking);


        otpService.sendOtp(booking.getBookingId(), email);

        return "Booking created successfully. BookingId: " + booking.getBookingId() + ". OTP sent to email.";
    }


    public String verifyOtpAndConfirm(Long bookingId, String otp) {
        String otpResult = otpService.verifyOtp(bookingId, otp);

        if (!otpResult.equals("OTP verified successfully")) {
            return otpResult;
        }


        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    booking.setStatus(BookingStatus.CONFIRMED);
                    bookingRepository.save(booking);
                    return "Booking confirmed successfully";
                }).orElse("Booking not found for ID: " + bookingId);
    }

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }
}
