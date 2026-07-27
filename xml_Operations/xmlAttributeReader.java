import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlAttributeReader {
    public static void main(String[] args) {

        try {
            File file = new File("src/employee.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root Element: "
                    + document.getDocumentElement().getNodeName());
            NodeList list = document.getElementsByTagName("employee");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element employee = (Element) node;
                        String id = employee.getAttribute("id");
                        String department = employee.getAttribute("department");
                        String name = employee.getElementsByTagName("name").item(0).getTextContent();

                        String salary = employee.getElementsByTagName("salary").item(0).getTextContent();
                        System.out.println("ID: " + id);
                        System.out.println("Department: " + department);
                        System.out.println("Name: " + name);
                        System.out.println("Salary: " + salary);
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}