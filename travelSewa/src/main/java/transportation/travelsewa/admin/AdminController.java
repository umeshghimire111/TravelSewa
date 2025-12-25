package transportation.travelsewa.admin;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportation.travelsewa.entity.Bus;
import transportation.travelsewa.entity.Location;
import transportation.travelsewa.entity.Route;
import transportation.travelsewa.entity.Trip;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getLocations() {
        return ResponseEntity.ok(adminService.getAllLocations());
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        return ResponseEntity.ok(adminService.addLocation(location));
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        return ResponseEntity.ok(adminService.updateLocation(id, location));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        adminService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getRoutes() {
        return ResponseEntity.ok(adminService.getAllRoutes());
    }

    @PostMapping("/routes")
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        return ResponseEntity.ok(adminService.addRoute(route));
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        return ResponseEntity.ok(adminService.updateRoute(id, route));
    }

    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        adminService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/buses")
    public ResponseEntity<List<Bus>> getBuses() {
        return ResponseEntity.ok(adminService.getAllBuses());
    }

    @PostMapping("/buses")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(adminService.addBus(bus));
    }

    @PutMapping("/buses/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus bus) {
        return ResponseEntity.ok(adminService.updateBus(id, bus));
    }

    @DeleteMapping("/buses/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        adminService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTrips() {
        return ResponseEntity.ok(adminService.getAllTrips());
    }

    @PostMapping("/trips")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        return ResponseEntity.ok(adminService.addTrip(trip));
    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        return ResponseEntity.ok(adminService.updateTrip(id, trip));
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        adminService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
}
