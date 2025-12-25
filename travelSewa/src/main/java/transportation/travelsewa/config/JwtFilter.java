package transportation.travelsewa.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import transportation.travelsewa.entity.Role;
import transportation.travelsewa.entity.User;
import transportation.travelsewa.repository.RoleRepository;
import transportation.travelsewa.repository.UserRepository;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();


        if (isPublicEndpoint(path)) {
            filterChain.doFilter(request, response);
            return;
        }


        String token = extractToken(request);
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Authorization header");
            return;
        }


        if (!jwtUtils.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
            return;
        }

        String email = jwtUtils.getEmailFromToken(token);


        userRepository.findByEmail(email).ifPresentOrElse(user -> {

            if (user.getRole() == null) {
                Role defaultRole = roleRepository.findByName("USER")
                        .orElseThrow(() -> new RuntimeException("Default role not found"));
                user.setRole(defaultRole);
                userRepository.save(user);
                log.info("Assigned default ROLE_USER to user: {}", email);
            }


            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(formatRole(user.getRole().getName()));


            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            null,
                            Collections.singletonList(authority)
                    )
            );

            log.info("JWT authentication successful for user: {}", email);

        }, () -> {
            try {
                log.warn("User not found for JWT: {}", email);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
            } catch (IOException e) {
                log.error("JWT filter response error", e);
            }
        });

        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/auth")
                || path.startsWith("/api/forget")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/api/travel");
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer "))
                ? header.substring(7)
                : null;
    }

    private String formatRole(String role) {
        role = role.toUpperCase().trim();
        if (role.equals("ADMIN")) return "ROLE_ADMIN";
        if (role.equals("USER")) return "ROLE_USER";
        if (role.startsWith("ROLE_")) return role;
        return "ROLE_" + role;
    }
}
