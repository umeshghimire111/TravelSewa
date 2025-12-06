package transportation.travelsewa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="location")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    private String name;
}
