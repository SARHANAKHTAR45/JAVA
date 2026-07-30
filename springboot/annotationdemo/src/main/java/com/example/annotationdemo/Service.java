//Contains the buisness logic
package com.example.annotationdemo;

import org.springframework.stereotype.Service;

@Service
public class Service {
    public String getEmployee(){

        //contains all the buisness logic which will be passed to the repository

        return "employee";
    }
}
