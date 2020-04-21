package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author JonSnow
 * @Date 2020/4/21
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

}
