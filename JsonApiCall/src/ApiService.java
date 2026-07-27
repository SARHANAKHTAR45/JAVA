import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    public Todo getTodo() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                    .GET()
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            int userId = Integer.parseInt(json.split("\"userId\":")[1].split(",")[0].trim());
            int id = Integer.parseInt(json.split("\"id\":")[1].split(",")[0].trim());
            String title = json.split("\"title\":")[1].split(",")[0]
                    .replace("\"", "")
                    .trim();
            boolean completed = Boolean.parseBoolean(
                    json.split("\"completed\":")[1]
                            .replace("}", "")
                            .trim()
            );
            return new Todo(userId, id, title, completed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
