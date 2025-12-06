package transportation.travelsewa.service;



import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transportation.travelsewa.config.JwtUtils;
import transportation.travelsewa.dto.LoginRequest;
import transportation.travelsewa.dto.RequestResponse;
import transportation.travelsewa.dto.SignupRequest;
import transportation.travelsewa.entity.Role;
import transportation.travelsewa.entity.User;
import transportation.travelsewa.repository.RoleRepository;
import transportation.travelsewa.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    public RequestResponse signup(SignupRequest request) {
        String email = request.getEmail().trim();

        if (userRepository.existsByEmail(email)) {
            return new RequestResponse("Email taken", null);
        }
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole(role);
        userRepository.save(user);

        String accessToken = jwtUtils.generateToken(email);
        String refreshToken = jwtUtils.generateRefreshToken(email);

        return new RequestResponse(accessToken, refreshToken);
    }
    public RequestResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }


        String accessToken = jwtUtils.generateToken(user.getEmail());
        String refreshToken = jwtUtils.generateRefreshToken(user.getEmail());

        return new RequestResponse(accessToken, refreshToken);
    }


    public RequestResponse refreshToken(String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken))
            throw new RuntimeException("Invalid or expired refresh token");

        String email = jwtUtils.getEmailFromToken(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = jwtUtils.generateToken(user.getEmail());
        String newRefreshToken = jwtUtils.generateRefreshToken(user.getEmail());

        return new RequestResponse(newAccessToken, newRefreshToken);
    }
}