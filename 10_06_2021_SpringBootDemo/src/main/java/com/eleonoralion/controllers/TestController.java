package com.eleonoralion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/main")
    public String getTestMainPage(Model model){

        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);

        File dir = new File(currentPath);
        File[] files = dir.listFiles();
        List<File> filesName = Arrays.asList(files);

        for(File temp : filesName){
            System.out.println(temp.getName());
        }

        model.addAttribute("currentPath", currentPath);
        model.addAttribute("filesName", filesName);

        return "test/main";
    }
}
