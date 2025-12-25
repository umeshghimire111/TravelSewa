package transportation.travelsewa.bookingOtp;



import lombok.Data;

@Data
public class OtpRequest {
    private Long bookingId;
    private String otp;
    private String email;
}
