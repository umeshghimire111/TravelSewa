package transportation.travelsewa.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transportation.travelsewa.entity.Bus;
import transportation.travelsewa.entity.Location;
import transportation.travelsewa.entity.Route;
import transportation.travelsewa.entity.Trip;
import transportation.travelsewa.repository.BusRepository;
import transportation.travelsewa.repository.LocationRepository;
import transportation.travelsewa.repository.RouteRepository;
import transportation.travelsewa.repository.TripRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final TripRepository tripRepository;



    public List<Location> getAllLocations() {
        return locationRepository.findAll(); }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll(); }

    public List<Bus> getAllBuses() {
        return busRepository.findAll(); }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll(); }



    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip); }

    public Location updateLocation(Long id, Location location) {
        Location existing = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        existing.setName(location.getName());
        return locationRepository.save(existing);
    }

    public Route updateRoute(Long id, Route route) {
        Route existing = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        existing.setSource(route.getSource());
        existing.setDestination(route.getDestination());
        return routeRepository.save(existing);
    }

    public Bus updateBus(Long id, Bus bus) {
        Bus existing = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        existing.setName(bus.getName());
        existing.setCapacity(bus.getCapacity());
        existing.setRoute(bus.getRoute());
        return busRepository.save(existing);
    }

    public Trip updateTrip(Long id, Trip trip) {
        Trip existing = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        existing.setBus(trip.getBus());
        existing.setDepartureTime(trip.getDepartureTime());
        existing.setArrivalTime(trip.getArrivalTime());
        return tripRepository.save(existing);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}
