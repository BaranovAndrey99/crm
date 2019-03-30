package crm.controller;

import crm.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
