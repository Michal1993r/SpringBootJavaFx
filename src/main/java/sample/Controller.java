package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<String> displayStats(){
        return Arrays.asList(test.getReceivedData().split(","));
    }

}
