package com.example.multipart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {
    @PostMapping("/employee")
    public String addEmployee(@RequestBody Employee employee){
        System.out.println(employee.getId());
        System.out.println(employee.getName());
        System.out.println(employee.getAge());

        return "Employee recieved successfully!";
    }

    @PostMapping("/upload")
    public String upload(
        @RequestPart("employee") Employee employee,
        @RequestPart("file") MultipartFile file
    ) {
        System.out.println("Employee ID:"+employee.getId());
        System.out.println("Employee Name:"+employee.getName());
        System.out.println("Employee Age:"+employee.getAge());

        System.out.println("File Name:"+ file.getOriginalFilename());
        System.out.println("File Size:"+ file .getSize());  //returns the number of bytes

        return "Upload Successfull";
    }
}
