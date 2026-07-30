package com.example.annotationdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/*Instaed of writing request mapping with method we can use 
Get mapping  */
import org.springframework.web.bind.annotation.GetMapping;
/*instead of putting response body in each mapping we can use rest
controller*/
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;



@RestController // @Controller + @ResponseBody
public class EmployeeController {
    
    @GetMapping("/getEmployee")
    public String getEmployee(){
        return "employee";
    }

    @GetMapping("/getEmployeeById")
    public String getEmployeeById(@RequestParam Integer employeeId){
        return "employee with respect to the id";
    }

    @GetMapping("/getEmployeeByPathvariable")
    public String getEmployeeByPathvariable(@PathVariable Integer employeeId){
        return "employee with respect to path variable";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Employee employee){
        return "Employee Added";
    }

    @GetMapping("updateEmployee")
    public String updateEmployee(Employee employee){
        return "Employee updated";
    }  
    
    @GetMapping("deleteEmployee")
    public String deleteEmployee(Employee employee){
        return "Employee deleted!";
    }
}
