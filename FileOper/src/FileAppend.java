import java.io.FileOutputStream;
import java.io.IOException;

public class FileAppend {
    public static void main(String args[]) {
        String text = "\n Write the appended text here. Springboooooot";
        try (FileOutputStream output = new FileOutputStream("myfile.txt", true)) {
            output.write(text.getBytes());
            System.out.println("File has been successfully appended");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
