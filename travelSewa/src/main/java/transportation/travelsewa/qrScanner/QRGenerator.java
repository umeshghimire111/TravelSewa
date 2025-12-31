package transportation.travelsewa.qrScanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class QRGenerator {


    public static String generateQRCodeBase64(String data, String filePath) throws Exception {

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");


        BitMatrix matrix = new MultiFormatWriter().encode(
                data, BarcodeFormat.QR_CODE, 300, 300, hints
        );

        ByteArrayOutputStream qrCodeStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", qrCodeStream);


        try (FileOutputStream generateQR = new FileOutputStream(filePath)) {
            generateQR.write(qrCodeStream.toByteArray());
        }

        System.out.println("QR code saved at: " + filePath);


        return Base64.getEncoder().encodeToString(qrCodeStream.toByteArray());
    }


}

