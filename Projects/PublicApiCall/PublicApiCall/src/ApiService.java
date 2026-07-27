import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    public String getMessage(){
        try{
            HttpClient client=HttpClient.newHttpClient();
            HttpRequest request=HttpRequest.newBuilder()
            .uri(URI.create("https://api.github.com/zen"))
            .GET()
            .build();

            HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (Exception e){
            return "Error:" +e.getMessage();
        }
    }
}
