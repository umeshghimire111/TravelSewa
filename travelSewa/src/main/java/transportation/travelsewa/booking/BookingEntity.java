package transportation.travelsewa.booking;





import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long busId;
    private Long userId;
    private Integer seats;
    private LocalDate travelDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}

