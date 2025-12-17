package transportation.travelsewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transportation.travelsewa.entity.Trip;

public interface TripRepository  extends JpaRepository<Trip,Long> {


}
