package com.example.apidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDemoApplication implements CommandLineRunner {
    @Autowired
    private ApiService apiService;
    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }
    @Override
    public void run(String... args) {
        String message = apiService.getMessage();
        System.out.println(message);
    }
}
