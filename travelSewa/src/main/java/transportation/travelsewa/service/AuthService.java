package transportation.travelsewa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transportation.travelsewa.config.JwtUtils;
import transportation.travelsewa.dto.*;
import transportation.travelsewa.entity.*;
import transportation.travelsewa.repository.*;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    public RequestResponse signup(SignupRequest request) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!request.getEmail().matches(emailRegex)) {
            throw new RuntimeException("Email format not match");
        }

        if (request.getPassword().length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }

        if (userRepository.existsByEmail(request.getEmail().trim())) {
            throw new RuntimeException("Email taken");
        }
        User user = new User();
        user.setEmail(request.getEmail().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        userRepository.save(user);

        return new RequestResponse("Successfully signup", null);
    }

    public RequestResponse login(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return new RequestResponse("User not found", null);
        }

        User user = optionalUser.get();



        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new RequestResponse("Invalid password", null);
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return new RequestResponse("Login successful", token);
    }

    public RequestResponse logout() {
        return new RequestResponse("Logged out successfully", null);
    }

}


