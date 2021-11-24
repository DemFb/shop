package com.shop.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HomeController {

    @RequestMapping("/")
    public String index(){
        return "home/index";
    }
}
