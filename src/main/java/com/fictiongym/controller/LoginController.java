package com.fictiongym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MemberLogin")
public class LoginController {

    @RequestMapping("/Login")
    public String Login(){
        return "login";
    }
}
