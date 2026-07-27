package dev.sarhan;
import java.io.IOException;

import todo.TodoClient;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        TodoClient todoClient=new TodoClient();
        System.out.println(todoClient.findAll());
    }
}