package com.example.annotationdemo;

import org.springframework.stereotype.Repository;

@Repository
public class Repository {
    public String getEmployee(){
        //performs al the db operation and fetches the data
        return "employeeData";
    }
}
