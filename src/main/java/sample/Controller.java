package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    Pi4jTest test;

    @RequestMapping("/")
    public String direction(Model model) {
        return "stats";
    }

    @RequestMapping("/gyroData")
    @ResponseBody
    public List<String> displayStats() {
        try {
            test.sendCommand('a');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(test.getGyroData().trim().split(","));
    }

    @RequestMapping("/gpsData")
    @ResponseBody
    public List<Double> getGPS() {
        String[] gpsData = test.getGpsData().split(",");
        if (!gpsData[0].equals("")) {
            Double latitudeNat = Double.valueOf(gpsData[3].substring(0, gpsData[3].indexOf(".")-2));
            Double latitudePrec = Double.valueOf(gpsData[3].substring(gpsData[3].indexOf(".")-2));
            Double latitude = latitudeNat + latitudePrec / 60;

            Double longitudeNat = Double.valueOf(gpsData[5].substring(0, gpsData[5].indexOf(".")-2));
            Double longitudePrec = Double.valueOf(gpsData[5].substring(gpsData[5].indexOf(".")-2));
            Double longitude = longitudeNat + longitudePrec / 60;

            Double velocity = Double.valueOf(gpsData[7]);
            return Arrays.asList(latitude, longitude, velocity);
        }
        return Collections.emptyList();
    }

}
