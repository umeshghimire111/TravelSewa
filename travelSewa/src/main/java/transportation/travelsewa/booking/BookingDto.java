package transportation.travelsewa.booking;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingDto {

    @NotNull(message="required bus name")
    private String busName;

    @NotNull(message = "Please specify how many seats you want to book")
    @Min(value = 1, message = "You must book at least 1 seat")
    @Max(value = 40, message = "You cannot book more than 40 seats at once")
    private Integer seats;

    @NotNull(message = "Travel date is required")
    @FutureOrPresent(message = "Travel date must be today or next day")
    private LocalDate travelDate;
}