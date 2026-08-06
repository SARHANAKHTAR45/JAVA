import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileReader {
   public static void main(String[] args) {
       try {
           File obj = new File("myfile.txt");
           Scanner reader = new Scanner(obj);
           while (reader.hasNextLine()) {
               System.out.println(reader.nextLine());
           }
           reader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred while creating the file ");
           e.printStackTrace();
       }
   }
}