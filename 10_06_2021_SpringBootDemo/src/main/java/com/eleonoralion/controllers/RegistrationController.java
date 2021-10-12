package com.eleonoralion.controllers;

import com.eleonoralion.forms.UserRegistrationForm;
import com.eleonoralion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model, HttpSession session){
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String getRegistrationPage(UserRegistrationForm userRegistrationForm){
        userService.addUser(userRegistrationForm);
        return "redirect:/login?successMessage";
    }
}
