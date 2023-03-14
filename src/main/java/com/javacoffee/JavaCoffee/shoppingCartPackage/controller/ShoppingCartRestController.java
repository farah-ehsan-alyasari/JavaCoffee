package com.javacoffee.JavaCoffee.shoppingCartPackage.controller;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.service.ItemService;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.security.UserPrincipal;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import com.javacoffee.JavaCoffee.shoppingCartPackage.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartServices cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    //TODO:Connect this method to the add item to cart button
    //add product to shopping cart
    @PostMapping("/add-to-cart/{itemId}/{qty}")
    public String addItem(@PathVariable("itemId") Long itemId, @PathVariable("qty") Integer quantity, @AuthenticationPrincipal UserPrincipal userPrincipal){

        //if(userPrincipal==null || userPrincipal instanceof AnonymousAuthenticationToken){
        if(userPrincipal==null ){
            return "You must login to add this product to your shopping cart";
        }


        User user = userService.getCurrentlyLoggedInUser(userPrincipal);

        //do not add item to cart if inStockNumber on edge case
        Integer currentInStockNumber = itemService.findOne(itemId).getInStockNumber();
        if(currentInStockNumber - quantity < 0){
            return "sorry, not enough items in stock";
        }

        Integer addedQuantity = cartService.addItem(itemId, quantity, user);

        //return addedQuantity+ " item(s) of this product were added to your shopping cart";

        return quantity+ " item(s) of this product were added to your shopping cart";

    }

   /* @PostMapping("/update-cart/{itemId}/{qty}")
    public String updateQuantity(@PathVariable("itemId") Long itemId, @PathVariable("qty") Integer quantity, @AuthenticationPrincipal UserPrincipal userPrincipal){

        //if(userPrincipal==null || userPrincipal instanceof AnonymousAuthenticationToken){
        if(userPrincipal==null ){
            return "You must login to add this product to your shopping cart";
        }


        User user = userService.getCurrentlyLoggedInUser(userPrincipal);

        //do not add item to cart if inStockNumber on edge case
        Integer currentInStockNumber = itemService.findOne(itemId).getInStockNumber();
        if(currentInStockNumber - quantity < 0){
            return "sorry, not enough items in stock";
        }

       cartService.updateQuantity(itemId, quantity, user);

        //return addedQuantity+ " item(s) of this product were added to your shopping cart";

        return "item has been updated";

    }*/

    @Transactional
    @PostMapping("/update-cart/{itemId}/{qty}")
    public ResponseEntity<String> updateQuantity(@PathVariable("itemId") Long itemId, @PathVariable("qty") Integer quantity, @AuthenticationPrincipal UserPrincipal userPrincipal){

        //if(userPrincipal==null || userPrincipal instanceof AnonymousAuthenticationToken){
        if(userPrincipal==null ){
            return new ResponseEntity<>("You must login to add this product to your shopping cart", HttpStatus.UNAUTHORIZED);
        }

        User user = userService.getCurrentlyLoggedInUser(userPrincipal);

        //do not add item to cart if inStockNumber on edge case
        Integer currentInStockNumber = itemService.findOne(itemId).getInStockNumber();
        if(currentInStockNumber - quantity < 0){
            return new ResponseEntity<>("sorry, not enough items in stock", HttpStatus.BAD_REQUEST);
        }

        cartService.updateQuantity(itemId, quantity, user);

        return new ResponseEntity<>("item has been updated", HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/remove-cartItem/{itemId}")
    public String removeItemFromCart(@PathVariable("itemId") Long itemId,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal){
        if(userPrincipal == null){
            return "you must login to remove item";
        }

        User user = userService.getCurrentlyLoggedInUser(userPrincipal);

        if(user == null){
            return "you must login to remove item";
        }

        cartService.removeItem(itemId, user);

        return "item removed";
    }
}
