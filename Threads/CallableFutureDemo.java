import java.util.concurrent.*;

public class CallableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService executor= Executors.newFixedThreadPool(2);
        System.out.println("Starting the tasks");
        Callable<Integer> task1= ()->{
            System.out.println("Task 1 started by:"+Thread.currentThread().getName());
            Thread.sleep(2000);
            return 10*10;
        };

        Callable<Integer> task2= ()->{
            System.out.println("Task 2 started by"+Thread.currentThread().getName());
            Thread.sleep(1000);
            return 20*20;
        };

        Callable<Integer> task3= ()->{
            System.out.println("Task 2 started by"+Thread.currentThread().getName());
            Thread.sleep(1000);
            return 30*30;
        };

        Future<Integer> future1= executor.submit(task1);
        Future<Integer> future2= executor.submit(task2);
        Future<Integer> future3= executor.submit(task3);

        System.out.println("All the tasks are submitted.");
        System.out.println("Main thread is free to do other things");
        System.out.println("Main thread doing other work while waiting");

        System.out.println("Result of Task 1:"+ future1.get());
        System.out.println("Result of Task 2:"+ future2.get());
        System.out.println("Result of Task 3"+ future3.get());

        executor.shutdown();
        System.out.println("Done");
    }
}
