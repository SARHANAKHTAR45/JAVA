import java.io.FileWriter;
import java.io.IOException;
public class WritingInFile {
    public static void main(String[] args){
        try{
            FileWriter writer=new FileWriter("myfile.txt");
            writer.write("Hallo world, HALLO WORLD");
            writer.close();
            System.out.println("Successfully written");
        }catch(IOException e){
            System.out.println("An error occured while writing in the file");
            e.printStackTrace();
        }
    }
}
