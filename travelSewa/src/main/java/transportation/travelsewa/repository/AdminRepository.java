package transportation.travelsewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transportation.travelsewa.entity.Admin;

import java.util.List;

public interface AdminRepository  extends JpaRepository<Admin,Long> {

    List<Admin> findByEmail(String email);
}
