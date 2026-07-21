import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
public class jsonExporter-Downloader {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<HashMap<String, Object>> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            HashMap<String, Object> employee = new HashMap<>();

            System.out.println("\nEmployee " + (i + 1));

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
            sc.nextLine();
            employees.add(employee);
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper
                    .writerWithDefaultPrettyPrinter().writeValueAsString(employees);
            System.out.println("\nJSON Output");
            System.out.println(json);

            //Change after this 
            mapper.writerWithDefaultPrettyPrinter()
            .writeValue(new File("C:\\Users\\sarha\\Desktop\\JAVA\\objmapper\\employee.json"), employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
