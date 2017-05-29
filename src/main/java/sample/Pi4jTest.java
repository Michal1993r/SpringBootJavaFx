package sample;

import com.pi4j.io.serial.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Pi4jTest {

    private com.pi4j.io.serial.Serial bluetooth;
    private SerialConfig serialConfig;
    private String receivedData = "";

    public Pi4jTest() {
        bluetooth = SerialFactory.createInstance();
        bluetooth.addListener((SerialDataEventListener) serialDataEvent -> {
            try {
                receivedData = serialDataEvent.getAsciiString();
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
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public com.pi4j.io.serial.Serial getBluetooth() {
        return bluetooth;
    }

    public String getReceivedData() {
        return receivedData;
    }
}
