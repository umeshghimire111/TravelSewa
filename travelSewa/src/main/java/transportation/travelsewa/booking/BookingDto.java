package transportation.travelsewa.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    @NotNull(message = "Bus ID is required")
    private Long busId;

    @NotNull(message = "Seats are required")
    private Integer seats;

    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;
}
