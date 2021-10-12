package com.eleonoralion.controllers;

import com.eleonoralion.forms.UserRegistrationForm;
import com.eleonoralion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user", new UserRegistrationForm());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(UserRegistrationForm form){
        userService.addUser(form);
        return "redirect:/users";
    }

    @GetMapping("/userInfo/{login}")
    public String getUserInfo(@PathVariable String login, Model model){
        model.addAttribute("user", userService.findUsersByLogin(login));
        return "userInfo";
    }
}
