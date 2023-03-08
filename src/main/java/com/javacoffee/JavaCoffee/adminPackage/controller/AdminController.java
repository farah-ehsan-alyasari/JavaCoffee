package com.javacoffee.JavaCoffee.adminPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/manage-items")
    public String manageItems(){
        return "manage-items";
    }

    @RequestMapping("/add-new-item")
    public String addNewItem(){
        return "add-new-item";
    }

}
