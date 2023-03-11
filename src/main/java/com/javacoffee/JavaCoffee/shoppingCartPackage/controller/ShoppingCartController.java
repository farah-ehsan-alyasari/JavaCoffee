package com.javacoffee.JavaCoffee.shoppingCartPackage.controller;

import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.security.UserPrincipal;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.CartItem;
import com.javacoffee.JavaCoffee.shoppingCartPackage.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServices shoppingCartServices;

    @Autowired
    private UserService userService;

    @GetMapping("/shopping-cart")
    public String showShoppingCart(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){
        User user = userService.getCurrentlyLoggedInUser(userPrincipal);
        List<CartItem> cartItems = shoppingCartServices.listCartItems(user);

        model.addAttribute("cartItems", cartItems);

        return "shopping-cart";
    }

    /*@GetMapping("/shopping-cart")
    public String showShoppingCart(Model model, User user){
        User foundUser = userService.findUserByEmail(user.getEmail());
        List<CartItem> cartItems = shoppingCartServices.listCartItems(foundUser);

        model.addAttribute("cartItems", cartItems);

        return "shopping-cart";
    }*/
}
