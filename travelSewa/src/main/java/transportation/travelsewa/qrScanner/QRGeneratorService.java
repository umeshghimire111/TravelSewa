package transportation.travelsewa.qrScanner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transportation.travelsewa.booking.BookingRepository;
import java.io.File;
@Service
@RequiredArgsConstructor
public class QRGeneratorService {

    private final BookingRepository bookingRepository;

    public String generateBookingQR(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {

                    String qrData = "{" +"/n "

                            + "\"bookingId\":\"" + booking.getBookingId() + "\","
                            + "\"busId\":\"" + booking.getBusId() + "\","
                            + "\"userId\":\"" + booking.getUserId() + "\","
                            + "\"seats\":\"" + booking.getSeats() + "\","
                            + "\"travelDate\":\"" + booking.getTravelDate() + "\","
                            + "\"status\":\"" + booking.getStatus() + "\""
                            +"\n"+   "}";

                    try {

                        String folderPath = "/qrcodes";
                        File folder = new File(folderPath);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }


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
