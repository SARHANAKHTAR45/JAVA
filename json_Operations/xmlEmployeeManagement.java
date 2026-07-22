import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class xmlEmployeeManagement{
    static Document document;
    static String fileName;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter XML file name:");
        fileName=sc.nextLine();
        if(!fileName.endsWith(".xml")){
            fileName=".xml";
        }
        try{
            File file=new File(fileName);
            if(file.exists()){
                DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
                DocumentBuilder builder=factory.newDocumentBuilder();
                document=builder.parse(file);
                document.getDocumentElement().normalize();
            }else{
                createXML();
            }

            while(true){
                System.out.println("\n=========XML EMPLOYEE MANAGEMENT============\n");
                System.out.println("1. Add Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice){
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();

                        System.out.print("Enter Salary: ");
                        String salary = sc.nextLine();

                        addEmployee(id, name, dept, salary);

                        System.out.println("Employee Added Successfully.");
                        break;
                    
                    case 2:
                        displayEmployee();
                        break;

                    case 3:
                        System.out.println("Enter Employee ID:");
                        id=sc.nextLine();
                        System.out.println("Enter New Salary:");
                        salary=sc.nextLine();

                        updateEmployeeSalary(id, salary);
                        System.out.println("Salary Updated Successfully");
                        break;
                    
                    case 4:
                        System.out.println("Enter Employee ID to delete:");
                        id=sc.nextLine();
                        deleteEmployee(id);
                        System.out.println("Employee Deleted Successfully!");
                        break;

                    case 5:
                        saveXML();
                        System.out.println("XML SAVED SUCCESSFULLY!");
                        System.out.println("Program Closed!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice, please enter again !");
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void createXML() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document=builder.newDocument();
        Element root=document.createElement("employees");
        document.appendChild(root);
        saveXML();
    }

    static void addEmployee(String id, String name, String dept, String salary) throws Exception {
        Element employee=document.createElement("employee");

        Element empID=document.createElement("id");
        empID.appendChild(document.createTextNode(id));

        Element empName = document.createElement("name");
        empName.appendChild(document.createTextNode(name));

        Element empDepart=document.createElement("department");
        empDepart.appendChild(document.createTextNode(dept));

        Element empSal=document.createElement("salary");
        empSal.appendChild(document.createTextNode(salary));

        employee.appendChild(empID);
        employee.appendChild(empName);
        employee.appendChild(empDepart);
        employee.appendChild(empSal);

        document.getDocumentElement().appendChild(employee);
        saveXML();

        }

        static void updateEmployeeSalary(String id, String newSalary) throws Exception{
            NodeList list=document.getElementsByTagName("employee");
            for(int i=0; i<list.getLength(); i++){
                Element emp=(Element) list.item(i);
                String empId=emp.getElementsByTagName("id").item(0).getTextContent();
                if(empId.equals(id)){
                    emp.getElementsByTagName("salary").item(0).setTextContent(newSalary);
                    break;
                }
            }
            saveXML();
        }

        static void deleteEmployee(String id) throws Exception {
            NodeList list=document.getElementsByTagName("employee");
            for (int i=0; i<list.getLength(); i++) {
                Element emp=(Element) list.item(i);
                String empId=emp.getElementsByTagName("id")
                    .item(0).getTextContent();
                if (empId.equals(id)) {
                    emp.getParentNode().removeChild(emp);
                    break;
            }
        }
        saveXML();
    }

    static void displayEmployee(){
        NodeList list=document.getElementsByTagName("empployee");
        for(int i=0; i<list.getLength(); i++){
            Element emp= (Element) list.item(i);
            System.out.println("\n===================");

            System.out.println("ID:"+emp.getElementsByTagName("id").item(0).getTextContent());
            System.out.println("Name:"+emp.getElementsByTagName("name").item(0).getTextContent());
            System.out.println("Department:"+emp.getElementsByTagName("department").item(0).getTextContent());
            System.out.println("Salary:"+emp.getElementsByTagName("salary").item(0).getTextContent());
        
        }
    }  

    static void saveXML() throws Exception{
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer =transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result =new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

}


