package de.htwberlin.webtech;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class helloWorldController {
    // Daniels Version
    @GetMapping(path = "/HelloWorldPage")
    public ModelAndView showHelloWorld(){
    Map<String, Object> model = new HashMap<>();
    model.put("helloName", "Das ist mein Wert aus dem Model und Controller");
    return new ModelAndView("hello", model);
    }

    //Version aus den Folien vom Wider
    // @GetMapping(path = "/HelloWorldPage")
    //  public ModelAndView showHelloWorld(Model model){
    //    model.addAttribute("helloName", "Das ist mein Wert aus dem Model und Controller");
    //     return new ModelAndView("hello");
    // }
}
