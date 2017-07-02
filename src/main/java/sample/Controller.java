package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
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
    public List<String> getGPS() {
        try {
            test.sendCommand('g');
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] gpsData = test.getGpsData().split(",");
        if (!gpsData[0].equals("")) {
            Double lattitude = Double.valueOf(gpsData[3]) / 100;
            Double longitude = Double.valueOf(gpsData[5]) / 100;
            return Arrays.asList(lattitude.toString(), longitude.toString());
        }
        return Arrays.asList();
    }

}
