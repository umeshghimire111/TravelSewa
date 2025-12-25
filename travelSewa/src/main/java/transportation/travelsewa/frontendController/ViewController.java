package transportation.travelsewa;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Controller
@RequestMapping("/auth")
public class ViewController {


        @GetMapping("/login")
        public String loginPage() {
            return "auth/login";
        }

        @GetMapping("/signup")
        public String signupPage() {
            return "auth/signup";
        }

        @GetMapping("/forget-password")
        public String forgetPasswordPage() {
            return "auth/forget-password";
        }

        @GetMapping("/reset-password")
        public String resetPasswordPage() {
            return "auth/reset-password";
        }
    }


