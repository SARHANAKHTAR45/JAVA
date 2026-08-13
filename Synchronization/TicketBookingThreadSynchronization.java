class TicketBooking{
    private int availableTickets= 10; 
    public synchronized void bookTicket(int tickets){
        if (availableTickets >= tickets){
            availableTickets -= tickets;
            System.out.println(
                "Booked " + tickets
                + " tickets, Remaining tickets: "
                + availableTickets);
        }else{
            System.out.println(
                "Not enough tickets available to book "
                + tickets);
        }
    }

    public int getAvailableTickets(){
        return availableTickets;
    }
}

public class TicketBookingThreadSynchronization{
    public static void main(String[] args){
        TicketBooking booking
            = new TicketBooking(); 
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                booking.bookTicket(2); 
                try{
                    Thread.sleep(50); 
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++){
                booking.bookTicket(3); 
                try{
                    Thread.sleep(40);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Available Tickets: "+ booking.getAvailableTickets());
    }
}