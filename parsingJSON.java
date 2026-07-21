import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Employee> employees = parseEmployees(Path.of("json", "employeesnicsi.json"));

            for (Employee emp : employees) {
                System.out.println("Employee ID : " + emp.getEmp_id());
                System.out.println("Name        : " + emp.getName());
                System.out.println("Age         : " + emp.getAge());
                System.out.println("Salary      : " + emp.getSalary());

                String gender;
                switch (emp.getGender()) {
                    case 0:
                        gender = "Male";
                        break;
                    case 1:
                        gender = "Female";
                        break;
                    default:
                        gender = "Transgender";
                        break;
                }

                System.out.println("Gender      : " + gender);
                System.out.println("Number      : " + emp.getNumber());
                System.out.println("-------------------------------------");
            }

        } catch (IOException e) {
            System.err.println("Failed to read JSON file: " + e.getMessage());
        }
    }

    private static List<Employee> parseEmployees(Path jsonFile) throws IOException {
        String json = Files.readString(jsonFile).trim();
        if (!json.startsWith("[") || !json.endsWith("]")) {
            throw new IOException("Invalid JSON array format");
        }

        json = json.substring(1, json.length() - 1).trim();
        List<Employee> employees = new ArrayList<>();

        int index = 0;
        while (index < json.length()) {
            while (index < json.length() && Character.isWhitespace(json.charAt(index))) {
                index++;
            }
            if (index >= json.length()) {
                break;
            }
            if (json.charAt(index) == ',') {
                index++;
                continue;
            }
            if (json.charAt(index) != '{') {
                throw new IOException("Expected JSON object at index " + index);
            }

            int depth = 0;
            int start = index;
            int end = index;
            boolean inString = false;
            for (; end < json.length(); end++) {
                char c = json.charAt(end);
                if (c == '"') {
                    inString = !inString;
                } else if (!inString) {
                    if (c == '{') {
                        depth++;
                    } else if (c == '}') {
                        depth--;
                        if (depth == 0) {
                            break;
                        }
                    }
                }
            }
            if (depth != 0 || end >= json.length()) {
                throw new IOException("Unterminated JSON object starting at index " + start);
            }

            String objectText = json.substring(start + 1, end).trim();
            employees.add(parseEmployeeObject(objectText));
            index = end + 1;
        }

        return employees;
    }

    private static Employee parseEmployeeObject(String objectText) throws IOException {
        Employee employee = new Employee();
        List<String> pairs = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < objectText.length(); i++) {
            char c = objectText.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            }
            if (c == ',' && !inQuotes) {
                pairs.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            pairs.add(current.toString());
        }

        for (String pair : pairs) {
            String[] parts = pair.split(":", 2);
            if (parts.length != 2) {
                throw new IOException("Invalid JSON pair: " + pair);
            }
            String key = unquote(parts[0].trim());
            String value = parts[1].trim();

            switch (key) {
                case "emp_id" -> employee.setEmp_id(Integer.parseInt(value));
                case "name" -> employee.setName(unquote(value));
                case "age" -> employee.setAge(Integer.parseInt(value));
                case "salary" -> employee.setSalary(Integer.parseInt(value));
                case "gender" -> employee.setGender(Integer.parseInt(value));
                case "number" -> employee.setNumber(Long.parseLong(value));
                default -> throw new IOException("Unexpected JSON field: " + key);
            }
        }
        return employee;
    }

    private static String unquote(String text) {
        String trimmed = text.trim();
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"")) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return trimmed;
    }
}
