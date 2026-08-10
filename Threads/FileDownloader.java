import java.util.*;
import java.util.concurrent.*;

public class FileDownloader {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor= Executors.newFixedThreadPool(2);
        List<Future<String>> results= new ArrayList();
        String[] files={"a.txt", "b.txt", "c.txt", "d.txt", "e.txt"};
        for(String file:files){
            Callable<String> task= ()->{
                Thread.sleep(2000);     //After waiting for 2 seconds first 2 files get downloaded then another 2 and then the last file
                return "Downloaded"+file;
            };
            results.add(executor.submit(task));
        }
        for(Future<String> f: results){
            System.out.println(f.get());
        }
        executor.shutdown();
    }
}
