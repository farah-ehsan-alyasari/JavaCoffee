/*
package com.javacoffee.JavaCoffee.paymentPackage.controller;

import com.javacoffee.JavaCoffee.paymentPackage.utility.USConstants;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private UserService userService;

    @RequestMapping("/my-account")
    public String handleMyAccount(Model model, Principal principal){
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("user", user);


        //model.addAttribute("userPaymentList", user.getUserPaymentList());
        //model.addAttribute("userShippingList", user.getUserShippingList());
        //model.addAttribute("orderList", user.getOrderList());


        //UserShipping userShipping = new UserShipping();
        //model.addAttribute("userShipping", userShipping);

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        List<String> stateList = USConstants.listOfUsStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("classActiveEdit", true);


        return "my-account";
    }

}
*/