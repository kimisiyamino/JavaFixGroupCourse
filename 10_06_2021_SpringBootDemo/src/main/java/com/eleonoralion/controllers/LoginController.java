package com.eleonoralion.controllers;

import cn.apiclub.captcha.Captcha;
import com.eleonoralion.forms.UserLoginForm;
import com.eleonoralion.models.User;
import com.eleonoralion.services.UserService;
import com.eleonoralion.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(required = false) Map<String, Object> parameters){
        if(parameters.containsKey("error")){
            model.addAttribute("errorMessage", true);
        }
        if(parameters.containsKey("successMessage")){
            model.addAttribute("successMessage", true);
        }
        if(parameters.containsKey("errorCaptcha")){
            model.addAttribute("errorCaptcha", true);
        }

        model.addAttribute("userLoginForm", userService.getUserFormWithCaptcha(new UserLoginForm()));


        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@Valid UserLoginForm user, BindingResult bindingResult, Model model) {
        //////
        System.out.println(user);

        if(user.getCaptcha().equals(user.getHiddenCaptcha())) {
            if(bindingResult.hasErrors()){
                return "redirect:/login";
            }

            return "users";
        }

        return "redirect:/login?errorCaptcha";
    }


}
