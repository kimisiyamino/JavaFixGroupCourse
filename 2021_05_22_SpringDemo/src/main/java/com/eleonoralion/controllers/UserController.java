package com.eleonoralion.controllers;

import com.eleonoralion.dao.UserDao;
import com.eleonoralion.forms.UserForm;
import com.eleonoralion.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class UserController{

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public ModelAndView usersczxczx(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      @RequestParam(value = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView();

        //if(httpServletRequest.getMethod().equals("GET")) {
        List<User> users;
        if(name != null){
            users = userDao.findAllByFirstName(name);
        }else {
            users = userDao.findAll();
        }

        modelAndView.setViewName("show");
        modelAndView.addObject("usersFromServer", users);
        modelAndView.addObject("userForm", new UserForm());
        //}
        return modelAndView;
    }

    @GetMapping("/user/{id}")
    public ModelAndView userId(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      @PathVariable() Long id) {
        ModelAndView model = new ModelAndView();

        Optional<User> users = userDao.find(id);

        users.ifPresent(user -> model.addObject("usersFromServer", user));
        model.setViewName("show");

        return model;
    }

    @PostMapping("/users")
    public String addUser(UserForm userForm){
        userDao.save(User.from(userForm));
        return "redirect:/users";
    }
}
