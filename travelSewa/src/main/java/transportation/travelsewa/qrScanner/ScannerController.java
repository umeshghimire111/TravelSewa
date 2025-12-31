package transportation.travelsewa.qrScanner;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    public class ScannerController {
    @GetMapping("/public")
    public String publicPage() {
        return "QR Access Successful!";
    }
}
