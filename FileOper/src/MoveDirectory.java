import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class MoveDirectory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the current directory path: ");
        String sourcePath = sc.nextLine();
        System.out.print("Enter the destination folder path: ");
        String destinationPath = sc.nextLine();
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destinationPath, source.getFileName().toString());
            Files.move(source, destination);
            System.out.println("Directory moved successfully!");
            System.out.println("New Location: " + destination);
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
        sc.close();
    }
}