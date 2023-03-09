package com.korit.restaurant.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }

    @PostMapping("/login/error")
    public String loginError() {
        return "/account/login_error";
    }

    @GetMapping("/register")
    public String register() {
        return "/account/register";
    }
}