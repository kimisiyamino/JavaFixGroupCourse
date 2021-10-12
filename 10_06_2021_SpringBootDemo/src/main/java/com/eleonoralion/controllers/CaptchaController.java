package com.eleonoralion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaptchaController {

    @GetMapping("/cap")
    public String getCaptchaPage(Model model){
        String currentPath = System.getProperty("user.dir");
        model.addAttribute("currentPath", currentPath);

        return "captcha";
    }
}
