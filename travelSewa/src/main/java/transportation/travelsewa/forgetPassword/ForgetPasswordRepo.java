package transportation.travelsewa.forgetPassword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgetPasswordRepo extends JpaRepository<ForgetPasswordEntity,String> {

    Optional<ForgetPasswordEntity> findByEmail(String email);

}
