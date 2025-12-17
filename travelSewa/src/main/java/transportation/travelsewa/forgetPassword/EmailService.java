package transportation.travelsewa.forgetPassword;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transportation.travelsewa.entity.User;
import transportation.travelsewa.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Random;

@Service("forgetAuthService")
@RequiredArgsConstructor
public class EmailService {

    private final ForgetPasswordRepo forgetPasswordRepo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String sendOtp(String email) {
        userRepository.findByEmail(email).orElseThrow(()
                -> new RuntimeException("User not found"));


        String otp = String.format("%06d", new Random().nextInt(1_000_000));

        forgetPasswordRepo.deleteByEmail(email);

        ForgetPasswordEntity otpEntity = new ForgetPasswordEntity();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setFullName("");
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        forgetPasswordRepo.save(otpEntity);

        return "If this email exists, an OTP has been sent. Please check your inbox.";
    }

    public String resetPassword(String email, String otp, String newPassword) {
        ForgetPasswordEntity otpEntity = forgetPasswordRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid OTP or email."));

        if (!otpEntity.getOtp().equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP...Please check and try again.");
        }

        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP expired....Request a new one.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        forgetPasswordRepo.deleteByEmail(email);

        return "Your password has been successfully updated! You can now log in.";
    }
}
