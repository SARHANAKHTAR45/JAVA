package com.example.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.model.Employee;

@RestController
public class HelloController {

    private List<Employee> employees=new ArrayList<>();
    //A list that stores employees
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World";
    }

    @GetMapping("/employee")
    public Employee getEmployee(){
        return new Employee(1,"John", "IT");
    }

    @PostMapping("/employee")   //executes a post request
    public Employee addEmployee(@RequestBody Employee employee){
        /*Take the json from http request body, convert it into and employee 
        object and pass it into the method*/
        employees.add(employee);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employees;
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        for(int i=0; i<employees.size(); i++) {
            Employee employee=employees.get(i);
            if (employee.getId()==id) {
                employees.set(i, updatedEmployee);
                return updatedEmployee;
            }
        }   
    return null;
    }

    

}
