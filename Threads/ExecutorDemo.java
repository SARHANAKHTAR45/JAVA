import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorImp obj = new ExecutorImp();
        try {
            obj.execute(new NewThread());
        } catch (RejectedExecutionException | NullPointerException exception) {
            System.out.println(exception);
        }
    }
}

class ExecutorImp implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();  
    }
}

class NewThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread executed under an executor");
    }
}