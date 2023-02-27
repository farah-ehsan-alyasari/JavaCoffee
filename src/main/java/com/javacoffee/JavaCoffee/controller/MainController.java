package com.javacoffee.JavaCoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @RequestMapping("/myaccount")
    public String viewMyAccount(){
        return "my-account";
    }

}
