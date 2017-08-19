package sample;

import com.pi4j.io.serial.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Pi4jTest {

    private com.pi4j.io.serial.Serial bluetooth;
    private SerialConfig serialConfig;
    private String receivedData = "";
    private String gpsData = "";
    private String gyroData = "";
    private Boolean gpsInProgress = false;

    public Pi4jTest() {
        bluetooth = SerialFactory.createInstance();
        bluetooth.addListener((SerialDataEventListener) serialDataEvent -> {
            synchronized (this) {
                try {
                    receivedData = serialDataEvent.getAsciiString();
                    if (gpsInProgress) {
                        gpsInProgress = false;
                        gpsData = "$GPRMC," + receivedData.substring(0, receivedData.indexOf("\r"));
                    }
                    if (receivedData.contains("$GPRMC")) {
                        gpsData = "";
                        gpsInProgress = true;
                        String tempData = receivedData.substring(receivedData.indexOf("$"));
                        if (tempData.contains("\r\n")) {
                            gpsData = tempData.substring(tempData.indexOf("$"), tempData.indexOf("\r"));
                            gpsInProgress = false;
                        }
                    }
                    if (receivedData.contains("#GYRO")) {
                        gyroData = "";
                        String tempData = receivedData.substring(receivedData.indexOf("O") + 1);
                        if (tempData.contains("\r\n")) {
                            gyroData = tempData.substring(0, tempData.indexOf("\r"));
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            serialConfig = new SerialConfig();
            serialConfig.device(SerialPort.getDefaultPort())
                    .baud(Baud._115200)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1);
            bluetooth.open(serialConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGyroData() {
        return gyroData;
    }

    public synchronized void sendCommand(char command) throws IOException {
        bluetooth.write(command);
    }

    public String getGpsData() {
        return gpsData;
    }
}
