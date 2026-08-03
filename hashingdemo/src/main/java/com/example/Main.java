package com.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        UserDAO userDAO= new UserDAO();
        Scanner sc=new Scanner(System.in);
        System.out.println("==REGISTER==");
        System.out.println("Choose a unique username:");
        String username=sc.nextLine();
        System.out.println("Enter a strong password");
        String pass=sc.nextLine();

        boolean registered=userDAO.registerUser(username, pass);
        System.out.println(registered ? "Registration successful!" : "Registration failed.");

        System.out.println("\n==Login==");
        System.out.println("Enter Username:");
        String loginUsername=sc.nextLine();
        System.out.println("Enter password:");
        String loginPassword=sc.nextLine();

        boolean loggedIn=userDAO.loginUser(loginUsername, loginPassword);
        System.out.println(loggedIn ? "Login successful! Welcome, " + loginUsername + "."
        : "Login failed: invalid username or password.");
        sc.close();


    }
}
