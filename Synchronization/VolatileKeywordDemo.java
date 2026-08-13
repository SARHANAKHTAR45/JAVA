
class Counter{
    private volatile boolean running=true;
    public void stop(){
        running=false;
    }
    
    public void start() {
        new Thread(() -> {
            while (running) {
                System.out.println("Running...");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Stopped.");
        }).start();
    }
}

public class VolatileKeywordDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter= new Counter();
        counter.start();
        Thread.sleep(600);
        counter.stop();
    }
}
