package transportation.travelsewa.qrScanner;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/public")
    public class ScannerController {
    @GetMapping("/qr")
    public String publicPage() {
        return "QR Access Successful!";
    }
}
