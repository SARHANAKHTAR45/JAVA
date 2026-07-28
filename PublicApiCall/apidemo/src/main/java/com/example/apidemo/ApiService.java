package com.example.apidemo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    public String getMessage(){
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject("https://api.github.com/zen", String.class);
        return response;
    }
}
