package com.example.security.controller;

import com.example.security.model.vo.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author JonSnow
 * @Date 2020/4/21
 **/
@Controller
public class AuthenticationController {

    @GetMapping("/user")
    public String userPage() {
        return "/user/user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "/admin/admin";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "/index";
    }

    @GetMapping("/test")
    @ResponseBody
    public TestVO test() {
        if (true) {
            throw new RuntimeException("test 500");
        }
        return new TestVO("test");
    }
 }
