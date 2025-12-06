package transportation.travelsewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transportation.travelsewa.entity.Bus;

public interface BusRepository extends JpaRepository<Bus,Long> {
}
