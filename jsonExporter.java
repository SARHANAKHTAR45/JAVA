import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of Employee:");
        int n=sc.nextInt();

        Map<String, Object> employee = new HashMap<>();

        for(int i=0; i<n; i++){

            System.out.print("Enter Employee ID: ");
            employee.put("emp_id", sc.nextInt());
            sc.nextLine();

            System.out.print("Enter Name: ");
            employee.put("name", sc.nextLine());

            System.out.print("Enter Age: ");
            employee.put("age", sc.nextInt());

            System.out.print("Enter Salary: ");
            employee.put("salary", sc.nextInt());

            System.out.print("Enter Gender (0-Male, 1-Female, 2-Transgender): ");
            employee.put("gender", sc.nextInt());
            
            System.out.print("Enter Mobile Number: ");
            employee.put("number", sc.nextLong());

        }

        ObjectMapper mapper = new ObjectMapper();
        
        try {

            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employee);

            System.out.println("\nGenerated JSON:");
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}