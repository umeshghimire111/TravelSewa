package transportation.travelsewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transportation.travelsewa.entity.Trip;

import java.util.List;

public interface TripRepository  extends JpaRepository<Trip,Long> {


}
