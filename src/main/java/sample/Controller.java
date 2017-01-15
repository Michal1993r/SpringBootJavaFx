package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    Pi4jTest test;

    @RequestMapping("/")
    public void direction(@RequestParam(name = "direction", required = false) String param) {
        try {
            System.out.println(param + "\n");
            switch (param) {
                case "forward":
                    test.getBluetooth().write('w');
                    break;
                case "reverse":
                    test.getBluetooth().write('s');
                    break;
                case "left":
                    test.getBluetooth().write('a');
                    break;
                case "right":
                    test.getBluetooth().write('d');
                    break;
                case "stop":
                    test.getBluetooth().write('h');
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/speed")
    public void speedChange(@RequestParam(name = "value") String value) {
        try {
            System.out.println("New Speed value " + value + "%\n");
            test.getBluetooth().write('*');
            test.getBluetooth().write(Integer.valueOf(value).byteValue());
            test.getBluetooth().write('#');
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
