package transportation.travelsewa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="trip")

public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="bus_id")
    private Bus bus;

    @Column(nullable=false)
    private LocalDateTime departureTime;

    @Column(nullable=false)
    private LocalDateTime arrivalTime;

}
