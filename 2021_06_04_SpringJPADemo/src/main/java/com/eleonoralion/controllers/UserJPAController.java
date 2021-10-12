package com.eleonoralion.controllers;

import com.eleonoralion.forms.UserForm;
import com.eleonoralion.models.Car;
import com.eleonoralion.models.User;
import com.eleonoralion.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserJPAController {

    private UsersRepository usersRepository;

    @Autowired
    public UserJPAController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping("/jpa/users")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = usersRepository.findAll();

        modelAndView.setViewName("show");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @PostMapping("/jpa/users")
    public String add(UserForm userForm) {
        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();

        User user = User.builder().firstName(firstName).lastName(lastName).build();
        usersRepository.save(user);
        return "redirect:/jpa/users";
    }

    @GetMapping("/jpa/users/findAge")
    public ModelAndView getUserByAge(@RequestParam Integer age) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = usersRepository.findAllByAge(age);

        modelAndView.setViewName("show");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @GetMapping("/jpa/users/findAllName")
    public ModelAndView getUserByFirstAndLastName(@RequestParam String firstName,
                                                  @RequestParam String lastName) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = usersRepository.findAllByFirstNameAndLastName(firstName, lastName);

        modelAndView.setViewName("show");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }
}
