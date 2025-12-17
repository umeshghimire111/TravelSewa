package transportation.travelsewa.booking;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transportation.travelsewa.entity.Bus;
import transportation.travelsewa.repository.BusRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BusRepository busRepository;
    public Booking createBooking(BookingDto booking) {
        Bus bus = busRepository.findById(booking.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        if (booking.getSeats() > bus.getCapacity()) {
            throw new RuntimeException("sorry..../n Not enough seats available");
        }

        bus.setCapacity(bus.getCapacity()- booking.getSeats());
        busRepository.save(bus);

        Booking bookings = new Booking();
        bookings.setBus(bus);
        bookings.setUserId(booking.getUserId());
        bookings.setSeats(bookings.getSeats());
        bookings.setTravelDate(booking.getTravelDate());
        bookings.setStatus("CONFIRMED");

        return bookingRepository.save(bookings);
    }
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();

    }
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findAll().stream()
                .filter(b-> b.getUserId().equals(userId))
                .toList();
    }
}
