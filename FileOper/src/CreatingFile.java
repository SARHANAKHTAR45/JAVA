import java.io.File;
import java.io.IOException;
public class CreatingFile {
    public static void main(String[] args){
        try{
            File obj=new File("demoDelete.txt");
            if(obj.createNewFile()){
                System.out.println("File created"+obj.getName());
            }else{
                System.out.println("File already exists");
            }
        }catch(IOException e){
            System.out.println("An error occured while craeting the File!");
            e.printStackTrace();
        }
    }

}
