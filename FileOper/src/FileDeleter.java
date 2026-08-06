import java.io.File;
public class FileDeleter {
    public static void main(String[] args){
        File obj=new File("demoDelete.txt");
        if(obj.delete()){
            System.out.println("Deleted:"+obj.getName());
        }else{
            System.out.println("Failed to delete the file!");
        }
    }
}
