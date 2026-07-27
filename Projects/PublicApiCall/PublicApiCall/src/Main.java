
public class Main {
    public static void main(String[] args) {
        ApiService api = new ApiService();
        String message = api.getMessage();
        System.out.println("Message from API:");
        System.out.println(message);
    }
}