// Reading File using scanner
import java.io.File;
import java.util.Scanner;

public class InputOutputJava {
    public static void main(String[] args) {
        try{
            File file=new File("student.txt");
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                String line= sc.nextLine();
                System.out.println(line);
            }
            sc.close(); 
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
