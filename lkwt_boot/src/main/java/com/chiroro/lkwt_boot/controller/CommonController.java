package com.chiroro.lkwt_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;


/**
 * CommonController
 */
@Controller
@Slf4j
public class CommonController {

    @GetMapping("/login")
    public void GETLogin() {
        
    }

}