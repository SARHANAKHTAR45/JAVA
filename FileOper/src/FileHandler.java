import java.io.*;
import java.io.FileReader;
import java.util.*;

public class FileHandler {
    static Scanner sc= new Scanner(System.in);
    static File f=null;

    public static void main(String args[]){
        int ch;
        do{
            System.out.println("\n=======FILE HANDLING MENU========\n");
            System.out.println("1.Create a File");
            System.out.println("2.Write into a file");
            System.out.println("3.Read from a file");
            System.out.println("4.Delete a File");
            System.out.println("5.Change File Location");
            System.out.println("6.Exit");
            System.out.println("Enter your choice:");
            ch=Integer.parseInt(sc.nextLine().trim());

            switch(ch){
                case 1:
                    createFile();
                    break;
                case 2: 
                    writeFile();
                    break;
                case 3:
                    readFile();
                    break;
                case 4:
                    deleteFile();
                    break;
                case 5:
                    moveFile();
                    break;
                case 6:
                    System.out.println("Exiting.......");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }while(ch!=6);
    }

    static void createFile(){
        try{
            System.out.print("Enter file name to create:");
            String name=sc.nextLine().trim();
            f=new File(name);
            if(f.createNewFile()){
                System.out.println("File created: " + f.getAbsolutePath());
            }else{
                System.out.println("File already exists");
            }
        }catch(IOException e){
            System.out.println("Error creating file"+e.getMessage());
        }
    }

    static void writeFile(){
        System.out.print("Enter file name with format to start writing:");
        String name=sc.nextLine().trim();
        File file=new File(name);
        if(!file.exists()){
            System.out.println("File does not exist. Create it first.");
            return;
        }
        try(BufferedWriter bw= new BufferedWriter(new FileWriter(file, true))){
            System.out.println("Enter text to write and tyoe EXIT on a new line to stop");
            String line;
            while(!(line=sc.nextLine()).equalsIgnoreCase("EXIT")){
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Data Recorded successfully");
        }catch(IOException e){
            System.out.println("Error while writing into the file"+e.getMessage());
        }
    }

    static void readFile(){
        System.out.print("Enter filename to read:");
        String name=sc.nextLine().trim();
        File file=new File(name);
        if(!file.exists()){
            System.out.println("The following enter file does not exists!");
            return;
        }
        try(BufferedReader br= new BufferedReader(new FileReader(file))){
            String line;
            System.out.println("-----File Content-------");
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            System.out.println("----End of file content------");
        }catch (IOException e){
            System.out.println("Error in reading the file"+e.getMessage());
        }
    }

    static void deleteFile(){
        System.out.print("Enter the name of the file with format to delete");
        String name= sc.nextLine().trim();
        File file= new File(name);
        if(!file.exists()){
            System.out.println("File does not exists");
            return;
        }
        if(file.delete()){
            System.out.println("File has been deleted successfully!!");
        }else{
            System.out.println("Failed to delete the file.");
        }
    }

    static void moveFile(){
        System.out.print("Enter current file name:");
        String name =sc.nextLine().trim();
        File src= new File(name);
        if(!src.exists()){
            System.out.println("File does not exist");
            return;
        }
        System.out.print("Enter destination folder full pathname");
        String dest=sc.nextLine().trim();
        File destDir=new File(dest);
        File target;
        if(destDir.isDirectory()){
            target=new File(destDir, src.getName());
        }else{
            target= new File(dest);
        }
        if(src.renameTo(target)){
            System.out.println("File has been moved to the following directory"+ target.getAbsolutePath());
        }else{
            System.out.println("Failed to move the file from destination");
        }

    }
}
