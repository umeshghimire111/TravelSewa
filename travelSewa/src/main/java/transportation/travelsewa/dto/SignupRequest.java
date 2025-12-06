package transportation.travelsewa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignupRequest {

    private String name;
    private String email;
    private String password;

}
