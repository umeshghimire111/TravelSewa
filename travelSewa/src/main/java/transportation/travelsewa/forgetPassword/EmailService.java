package transportation.travelsewa.forgetPassword;



import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender mailSender;


    private void sendEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Forget Password OTP");
        message.setText("Your OTP is: " + otp + "\nValid for 5 minutes.");
        message.setFrom("travelsewa.noreply@gmail.com");

        mailSender.send(message);
    }
    public String sendOtp(String email) {
        userRepository.findByEmail(email)
                .orElseThrow(()
                -> new RuntimeException("User not found"));


        String otp = String.format("%06d", new Random().nextInt(60000));


        ForgetPasswordEntity otpEntity = forgetPasswordRepo.findByEmail(email)
                .map(existing -> {
                    existing.setOtp(otp);
                    existing.setExpiryTime(LocalDateTime.now().plusMinutes(1));

                    return existing;
                })
                .orElseGet(() -> {
                    ForgetPasswordEntity newEntity = new ForgetPasswordEntity();
                    newEntity.setEmail(email);
                    newEntity.setOtp(otp);
                    newEntity.setExpiryTime(LocalDateTime.now().plusMinutes(1));
                    return newEntity;
                });

        forgetPasswordRepo.save(otpEntity);
        sendEmail(email, otp);

        return "Your OTP is: " + otp + "\nValid for 1 minutes";

    }


    public String resetPassword(String email, String otp, String newPassword) {
        ForgetPasswordEntity entity =forgetPasswordRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));




        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("sorry\nUser not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password reset ";
    }

}
