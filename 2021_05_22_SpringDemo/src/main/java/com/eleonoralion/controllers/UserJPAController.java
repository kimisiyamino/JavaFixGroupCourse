package com.eleonoralion.controllers;

import com.eleonoralion.forms.UserForm;
import com.eleonoralion.models.Car;
import com.eleonoralion.models.User;
import com.eleonoralion.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserJPAController {

    private UsersRepository usersRepository;

    @Autowired
    public UserJPAController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping("/jpa/users")
    public ModelAndView getAll(@RequestParam(value = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = usersRepository.findAll();

        for(User u : users){
            System.out.println(u.getId());
            System.out.println(u.getFirstName());
            System.out.println(u.getLastName());
            System.out.println(u.getAge());
            List<Car> cars = u.getCars();
            System.out.print("Cars: ");
            for(Car c : cars){
                System.out.println(c.getModel());
            }
        }

        modelAndView.setViewName("show");
        modelAndView.addObject("usersFromServer", users);
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

}
