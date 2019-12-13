package com.chiroro.lkwt_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * SampleController
 */
@Controller
@RequestMapping("/sample/*")
public class SampleController {

    @GetMapping("/ex")
    public void ex(Model model){
        model.addAttribute("msg", "we here");
    }
}