package com.javacoffee.JavaCoffee.adminPackage.controller;

import com.javacoffee.JavaCoffee.adminPackage.DTO.ItemDTO;
import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.service.ItemService;
import com.javacoffee.JavaCoffee.securityPackage.DTO.UserDTO;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ItemController {

    private ItemService itemDetailService;

    @Autowired
    public ItemController(ItemService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    /*TODO: Confirm that I do not need this method*/
    @RequestMapping("/add")
    public String addItem(Model model){
        ItemDTO itemDTO = new ItemDTO();
        model.addAttribute("item", itemDTO);
        return "addItem";
    }

    @PostMapping("/add-item-process")
    public String addItemProcess(@Valid @ModelAttribute("itemDTO") ItemDTO itemDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            log.warn("Wrong attempt");
            return "add-new-item";
        }
        itemDetailService.create(itemDTO);
        return "add-new-item";
    }

    @GetMapping("/manage-items")
    public String itemList(Model model){
        List<Item> itemList = itemDetailService.findAll();
        System.out.println("LIST:" + itemList);
        model.addAttribute("itemList", itemList);
        return "manage-items";
    }
}
