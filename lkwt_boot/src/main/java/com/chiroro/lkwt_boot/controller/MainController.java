package com.chiroro.lkwt_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MainController
 */
@Controller
public class MainController {

    @RequestMapping("/main")
    public void getMain(Model model){
        model.addAttribute("userName", "tester"+(char)('A'+(int)(Math.random()*21)));
    }

    
}