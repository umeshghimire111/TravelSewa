package transportation.travelsewa.booking;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "booked_booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @NotNull(message = "You must select a bus for your booking")
    private Long busId;

    @NotNull(message = "User information is required to make a booking")
    private Long userId;

    @NotNull(message = "Please specify how many seats you want to book")
    @Min(value = 1, message = "You must book at least 1 seat")
    @Max(value = 40, message = "You cannot book more than 40 seats at once")
    private Integer seats;

    @NotNull(message = "Travel date is required")
    @FutureOrPresent(message = "Travel date must be today or in the future")
    private LocalDate travelDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
