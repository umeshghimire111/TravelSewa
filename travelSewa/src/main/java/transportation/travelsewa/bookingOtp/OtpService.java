package transportation.travelsewa.bookingOtp;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final JavaMailSender mailSender;

    private static final long OTP_VALID_MINUTES = 1;


    public String sendOtp(Long bookingId, String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        BookingOtpEntity entity = new BookingOtpEntity();
        entity.setBookingId(bookingId);
        entity.setOtp(otp);
        entity.setExpiryTime(LocalDateTime.now().plusMinutes(OTP_VALID_MINUTES));

        otpRepository.save(entity);


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("TravelSewa Booking OTP");
        mail.setText("Your OTP is: " + otp + " (valid for " + OTP_VALID_MINUTES + " minute)");
        mailSender.send(mail);

        return "OTP sent successfully";
    }


    public String verifyOtp(Long bookingId, String inputOtp) {
        BookingOtpEntity otpEntity = otpRepository
                .findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (LocalDateTime.now().isAfter(otpEntity.getExpiryTime())) {
            otpRepository.delete(otpEntity);
            return "OTP expired";
        }

        if (!otpEntity.getOtp().equals(inputOtp)) {
            return "Invalid OTP";
        }

        otpRepository.delete(otpEntity);
        return "OTP verified successfully";
    }
}
