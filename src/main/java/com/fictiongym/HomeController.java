package com.fictiongym;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/gymstaff")
    public String gymStaff(){
        return "gymstaff";
    }
}
