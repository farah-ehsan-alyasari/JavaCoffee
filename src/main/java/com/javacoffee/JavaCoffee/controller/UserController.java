package com.javacoffee.JavaCoffee.controller;

/**
This controller is mapped to “/sign-up” URI. We use the UserDto to process and validate the user registration form and inject it using the @ModelAttribute("userDto") annotation. When the form is submitted it’s automatically validated and errors are available in the BindingResult. If the form has any errors, we return to the registration page. Otherwise, we redirect and inform the user the registration procedure is complete.
 * */

import com.javacoffee.JavaCoffee.DTO.UserDTO;
import com.javacoffee.JavaCoffee.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    private UserService userDetailsService;

    @Autowired
    public UserController(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    private String redirectToLogin()
    {
        return "redirect:/login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model)
    {
        model.addAttribute("userDto", new UserDTO());
        return "sign-up";
    }

    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute ("userDto") UserDTO userDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            log.warn("Wrong attempt");
            return "sign-up";
        }
        userDetailsService.creat(userDTO);
        return "confirmation";
    }

    /**
     * In order to make code more readable it is good practice to
     * use special DTOs for login It also make controllers
     * less dependent from entities and separate validation from
     * jpa functionality
     * @return
     */
    @RequestMapping("/login")
    public String getLoginPage()
    {
        log.info("Login page displayed");
        return "login";
    }

    @RequestMapping("/home")
    public String getHome()
    {
        return "home";

    }

}


