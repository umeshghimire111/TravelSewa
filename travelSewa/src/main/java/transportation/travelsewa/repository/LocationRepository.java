package transportation.travelsewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transportation.travelsewa.entity.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
