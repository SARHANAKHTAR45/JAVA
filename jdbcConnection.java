
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class jdbcConnection {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "root";

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC Driver Loaded Successfully!");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Established Successfully!");

            while (true) {
                System.out.println("\n========== EMPLOYEE MENU ==========");
                System.out.println("1. View Employees");
                System.out.println("2. Add Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. View Gender");
                System.out.println("6. Export Data as JSON");
                System.out.println("7. Exit");
                System.out.print("Enter Choice : ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        viewEmployee(con);
                        break;
                    case 2:
                        addEmployee(con);
                        break;
                    case 3:
                        updateEmployee(con);
                        break;
                    case 4:
                        deleteEmployee(con);
                        break;
                    case 5:
                        returnGender(con);
                        break;
                    case 6:
                        downloadJSON(con);
                        break;
                    case 7:
                        con.close();
                        sc.close();
                        System.out.println("Connection Closed Successfully.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid Choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewEmployee(Connection con) throws Exception {
        String sql = "SELECT * FROM nicsi ORDER BY emp_id";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("EMP_ID\tNAME\t\tAGE\tSALARY\tGENDER");
        while (rs.next()) {
            System.out.println(
                    rs.getInt("emp_id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getInt("age") + "\t" +
                    rs.getInt("salary") + "\t" +
                    rs.getInt("gender"));
        }
        rs.close();
        stmt.close();
    }

    static void addEmployee(Connection con) throws Exception {
        System.out.print("Enter Employee ID : ");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name : ");
        String name = sc.nextLine();
        System.out.print("Enter Age : ");
        int age = sc.nextInt();
        System.out.print("Enter Salary : ");
        int salary = sc.nextInt();
        System.out.print("Enter Gender (0-Male,1-Female,2-Transgender): ");
        int gender = sc.nextInt();
        String sql = "INSERT INTO nicsi(emp_id,name,age,salary,gender) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, empId);
        ps.setString(2, name);
        ps.setInt(3, age);
        ps.setInt(4, salary);
        ps.setInt(5, gender);
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Employee Added Successfully.");
        else
            System.out.println("Failed to Add Employee.");
        ps.close();
    }

    static void updateEmployee(Connection con) throws Exception {
        System.out.print("Enter Employee ID : ");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Name : ");
        String name = sc.nextLine();
        System.out.print("Enter New Age : ");
        int age = sc.nextInt();
        System.out.print("Enter New Salary : ");
        int salary = sc.nextInt();
        System.out.print("Enter New Gender (0-Male,1-Female,2-Transgender): ");
        int gender = sc.nextInt();
        String sql = "UPDATE nicsi SET name=?, age=?, salary=?, gender=? WHERE emp_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setInt(3, salary);
        ps.setInt(4, gender);
        ps.setInt(5, empId);
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Employee Updated Successfully.");
        else
            System.out.println("Employee Not Found.");
        ps.close();
    }

    static void deleteEmployee(Connection con) throws Exception {
        System.out.print("Enter Employee ID : ");
        int empId = sc.nextInt();
        String sql = "DELETE FROM nicsi WHERE emp_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, empId);
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Employee Deleted Successfully.");
        else
            System.out.println("Employee Not Found.");
        ps.close();
    }

    static void returnGender(Connection con) throws Exception {
        String sql = "SELECT emp_id, name, return_gender(gender) AS gender FROM nicsi ORDER BY emp_id";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("\n----------------------------------------");
        System.out.println("EMP_ID\tNAME\t\tGENDER");
        System.out.println("----------------------------------------");
        while (rs.next()) {
            System.out.println(
                    rs.getInt("emp_id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getString("gender"));
        }

        rs.close();
        stmt.close();
    }

    static void downloadJSON(Connection con) throws Exception{
        String sql = "SELECT json_agg(nicsi) FROM nicsi";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            String json=rs.getString(1);
            FileWriter fw=new FileWriter("employeesnicsi.json");
            fw.write(json);
            fw.close();
            System.out.println("JSON FILE CREATED SUCCESSFULLY");
        }
        rs.close();
        stmt.close();
    }

    
}