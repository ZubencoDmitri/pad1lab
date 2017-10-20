package xPlatform;


public class ReceiverMessageReaderThread implements Runnable{
    private IOperation transport;
    public ReceiverMessageReaderThread(IOperation transport){
        this.transport=transport;
    }
    public void run() {
        String messageFromServer;   //Message from server
        while(!(messageFromServer = transport.readAsync()).equals("disconnect"))
            System.out.println(messageFromServer); // displaying at DOS prompt
        System.out.println("Disconnected from broker");
    }

}
