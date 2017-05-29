package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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
    public String displayStats(){
        return test.getReceivedData();
    }

}
