package transportation.travelsewa.frontendController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/details")
    public String details() {
        return "details";
    }
}
