package com.example.AuthenticationDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Welcome Admin User, You are Successfully Authenticated !";
    }

}
