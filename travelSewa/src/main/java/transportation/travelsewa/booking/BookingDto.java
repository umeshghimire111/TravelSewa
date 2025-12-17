package transportation.travelsewa.booking;


import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDto {
    private Long busId;
    private Long userId;
    private int seats;
    private LocalDate travelDate;

}