package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author JonSnow
 * @Date 2020/4/21
 **/
@RestController
public class AuthenticationController {

    @GetMapping("/user")
    public String userPage() {
        return "this is user page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "this is admin page";
    }

}
