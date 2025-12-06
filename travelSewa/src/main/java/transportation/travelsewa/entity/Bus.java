package transportation.travelsewa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
}
