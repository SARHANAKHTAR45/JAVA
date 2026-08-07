import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryCopy {
    public static void main(String[] args){
         try (FileInputStream input = new FileInputStream("C:\\Users\\sarha\\Desktop\\JAVA\\demo_copy.jpeg");
         FileOutputStream output = new FileOutputStream("C:\\Users\\sarha\\Desktop\\JAVA\\copy.jpeg")) {
            int b;
            while((b=input.read())!=-1){
                output.write(b);
            }
            System.out.println("File Copied Successfully!");
         }catch(IOException e){
            System.out.println("Error handling file");
         }
    }
}
