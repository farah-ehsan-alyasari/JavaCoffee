package com.javacoffee.JavaCoffee.storeFrontPackage.controller;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
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

    @RequestMapping("/item-details/{id}")
    public String showItemDetails(@PathVariable Long id, Model model) {
        // Get the item with a specific ID (e.g. ID 1) from the database
        Item item = itemDetailService.findOne(id); // replace 1L with the ID of the item you want to display

        // Add the item to the Thymeleaf model
        model.addAttribute("item", item);

        // Add a list of available quantities and sizes to the Thymeleaf model
        model.addAttribute("qtyList", Arrays.asList(1, 2, 3, 4, 5));
        //model.addAttribute("sizeList", Arrays.asList("Small", "Medium", "Large"));

        // Render the item-details template
        return "item-details";
    }

    /*@RequestMapping("/shopping-cart")
    public String showShoppingCart(){
        return "shopping-cart";
    }*/



}
