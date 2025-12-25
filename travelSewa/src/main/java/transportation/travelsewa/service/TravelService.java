package transportation.travelsewa.service;

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
public class TravelService {
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final TripRepository tripRepository;

    public List<Location> getAllLocations()
    { return locationRepository.findAll(); }

    public List<Route> getAllRoutes()
    { return routeRepository.findAll(); }

    public List<Bus> getAllBuses()
    { return busRepository.findAll(); }

    public List<Trip> getAllTrips()
    { return tripRepository.findAll(); }
}

