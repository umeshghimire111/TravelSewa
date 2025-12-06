package transportation.travelsewa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transportation.travelsewa.entity.Bus;
import transportation.travelsewa.entity.Location;
import transportation.travelsewa.entity.Route;
import transportation.travelsewa.entity.Trip;
import transportation.travelsewa.service.TravelService;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return travelService.getAllLocations();
    }

    @GetMapping("/routes")
    public List<Route> getRoutes() {
        return travelService.getAllRoutes();
    }

    @GetMapping("/buses")
    public List<Bus> getBuses() {
        return travelService.getAllBuses();
    }

    @GetMapping("/trips")
    public List<Trip> getTrips() {
        return travelService.getAllTrips();
    }
}
