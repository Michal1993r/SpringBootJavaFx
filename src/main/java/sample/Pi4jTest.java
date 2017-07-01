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

    public Pi4jTest() {
        bluetooth = SerialFactory.createInstance();
        bluetooth.addListener((SerialDataEventListener) serialDataEvent -> {
            try {
                receivedData = serialDataEvent.getAsciiString();
                if (receivedData.contains("$GPRMC")){
                    gpsData = "";
                    gpsData.concat(receivedData.substring(receivedData.indexOf('$')));
                    while (!gpsData.contains("\r\n")){
                        gpsData.concat(serialDataEvent.getAsciiString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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

    public String getReceivedData() {
        return receivedData;
    }

    public synchronized void sendCommand(char command) throws IOException {
        bluetooth.write(command);
    }

    public String getGpsData() {
        return gpsData;
    }
}
