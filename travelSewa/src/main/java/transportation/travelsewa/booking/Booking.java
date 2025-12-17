package transportation.travelsewa.booking;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import transportation.travelsewa.entity.Bus;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="booking")

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long seats;

    @Column(nullable = false)
    private LocalDate travelDate;

    @Column(nullable = false)
    private String status;
}
