package com.example.restapi.model;

public class Employee {
    //employee data 
    private int id;
    private String name;
    private String department;

    public Employee(){  //Default Constructor

    }
    /*When spring recieves json, it first creates an empty object, without
    this spring cannot create and object*/

    public Employee(int id, String name, String department){
        //creating employee object
        //Employee emp= new Employee(1, "John", "IT") 
        this.id=id;
        this.name=name;
        this.department=department;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDepartment(){
        return department;
    }

    //setters
    /*Setters are used to spring fills in each fields using the methods */
    public void setId(int id){
        this.id=id;
    }

    public void seName(String name){
        this.name=name;
    }

    public void setDepartment(String department){
        this.department=department;
    }
}   
