package transportation.travelsewa.qrScanner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transportation.travelsewa.booking.BookingRepository;
import transportation.travelsewa.repository.BusRepository;
import transportation.travelsewa.repository.UserRepository;
import java.io.File;
import org.json.JSONObject;

@Service
@RequiredArgsConstructor
public class QRGeneratorService {

    private final BookingRepository bookingRepository;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    public String generateBookingQR(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    try {

                        String busName = busRepository.findById(booking.getBusId())
                                .map(bus -> bus.getName())
                                .orElse("Unknown Bus");

                        String routeName = busRepository.findById(booking.getBusId())
                                .map(bus -> bus.getRoute())
                                .map(route -> route.getSource() + " → " + route.getDestination())
                                .orElse("Unknown Route");

                        JSONObject scanner = new JSONObject();
                        scanner.put("bookingId", booking.getBookingId());
                        scanner.put("userName", userRepository.findById(booking.getUserId())
                                .map(user -> user.getName())
                                .orElse("Unknown User"));
                        scanner.put("busName", busName);
                        scanner.put("routeName", routeName);
                        scanner.put("seats", booking.getSeats());
                        scanner.put("travelDate", booking.getTravelDate().toString());
                        scanner.put("status", booking.getStatus().name());

                        String qrData = scanner.toString();

                        String folderPath = "qrCodes";
                        File folder = new File(folderPath);
                        if (!folder.exists()) folder.mkdirs();

                        String filePath = folderPath + "/booking_" + booking.getBookingId() + ".png";


                        return QRGenerator.generateQRCodeBase64(qrData, filePath);

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .orElse(null);
    }
}
