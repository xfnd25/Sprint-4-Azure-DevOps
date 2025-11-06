package com.mottu.motolocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // Esta string "login" diz ao Thymeleaf para procurar e renderizar
        // o arquivo /resources/templates/login.html
        return "login";
    }
}