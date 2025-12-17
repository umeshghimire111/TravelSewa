package transportation.travelsewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transportation.travelsewa.entity.Route;


public interface RouteRepository extends JpaRepository<Route,Long> {
}
