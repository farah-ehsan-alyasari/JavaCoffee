package com.javacoffee.JavaCoffee.storeFrontPackage.controller;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StoreFrontController {

    private ItemService itemDetailService;

    @Autowired
    public StoreFrontController(ItemService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @RequestMapping("/order-online")
    public String orderOnline(Model model){
        List<Item> itemList = itemDetailService.findAll();
        model.addAttribute("itemList", itemList);
        return "order-online";
    }

}
