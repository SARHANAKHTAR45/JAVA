
class BankAccount{
    private int balance=1000;

    public synchronized void deposit(int amount){
        balance+=amount;
        System.out.println("Money Deposited: "+ amount+ " Balance: "+ balance);
    }

    public synchronized void withdraw(int amount){
        if(balance>=amount){
            balance=balance-amount;
            System.out.println("Money Withdrawn: "+ amount+ " Balance: " + balance);
        }else{
            System.out.println("Insufficient balance in account");
            System.out.println("Current balance is:"+ balance);
        }
    }

    public int getBalance(){
        return balance;
    }
}

public class BankAccountSynchronization {
    public static void main(String[] args){
        BankAccount account= new BankAccount();
        Thread t1= new Thread(() -> {
            for(int i=0; i<3; i++){
                account.deposit(200);
                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2= new Thread(() ->{
            for(int i=0; i<3; i++){
                account.withdraw(100);
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        System.out.println("The final account in the balanace is:"+ account.getBalance());
    }
}
