package transportation.travelsewa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transportation.travelsewa.config.JwtUtils;
import transportation.travelsewa.dto.*;
import transportation.travelsewa.entity.*;
import transportation.travelsewa.repository.*;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public RequestResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail().trim()))
            throw new RuntimeException("Email taken");

        Role role = roleRepository.findByName("USER").orElseThrow();

        User user = new User();
        user.setEmail(request.getEmail().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole(role);
        userRepository.save(user);

        String accessToken = jwtUtils.generateToken(user.getEmail());

        return new RequestResponse("success", accessToken);
    }


    public RequestResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(()
                        -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request
                .getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtUtils
                .generateToken(user.getEmail());

        return new RequestResponse("success :", accessToken);
    }

    public RequestResponse logout() {
        return new RequestResponse("Logged out", null);
    }
}


