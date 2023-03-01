package com.javacoffee.JavaCoffee.adminPackage.controller;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

    @RequestMapping("/add")
    public String addItem(Model model){
        Item item = new Item();
        model.addAttribute("item", item);
        return "addItem";
    }
}
