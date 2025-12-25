package transportation.travelsewa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="user")

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column( nullable = false)
    private String password;

    @ManyToOne
   @JoinColumn(name = "role_id")
    private Role role;

    private String tests;
}
