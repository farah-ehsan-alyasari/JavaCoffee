package com.javacoffee.JavaCoffee.shoppingCartPackage.controller;

import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.security.UserPrincipal;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import com.javacoffee.JavaCoffee.shoppingCartPackage.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartServices cartService;

    @Autowired
    private UserService userService;

    //TODO:Connect this method to the add item to cart button
    //add product to shopping cart
    @PostMapping("/add-to-cart/{itemId}/{qty}")
    public String addItem(@PathVariable("itemId") Long itemId, @PathVariable("qty") Integer quantity, @AuthenticationPrincipal UserPrincipal userPrincipal){

        //if(userPrincipal==null || userPrincipal instanceof AnonymousAuthenticationToken){
        if(userPrincipal==null ){
            return "You must login to add this product to your shopping cart";
        }

        User user = userService.getCurrentlyLoggedInUser(userPrincipal);

        Integer addedQuantity = cartService.addItem(itemId, quantity, user);
        System.out.println("YES:: :)");

        return addedQuantity+ "item(s) of this product were added to your shopping cart";

    }
}
