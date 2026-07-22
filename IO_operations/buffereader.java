//Bufereed Reader is faster because it reads a block of data into memory instead of one 
//character at a time
import java.io.BufferedReader;
import java.io.FileReader;
public class buffereader {
    public static void main(String[] args){
        try{
            BufferedReader br= new BufferedReader(new FileReader("student.txt"));
            String line;
            while((line =br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
