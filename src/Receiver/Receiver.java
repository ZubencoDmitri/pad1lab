package Receiver;

import xPlatform.IOperation;
import xPlatform.ReceiverMessageReaderThread;
import xPlatform.TransportService;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Semaphore;


public class Receiver {
    public static void main(String[] args) throws IOException {
        Socket s;                   //Server socket to connect
        String command;             //Receiver's command
        String name;                //Receiver's name
        String message;             //Receiver's message

        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("RECEIVER OPTIONS:");
        s = new Socket("localhost", 1488);
        System.out.println("--Input ur name--");
        name=buffRead.readLine();
        IOperation receiver=new TransportService(s);
        System.out.println("--Input \"connect\" command to be connected to broker--");
        command=buffRead.readLine();
        while(true)
            if(command.equals("connect"))
            {
                message=command+" "+name+"\n";
                receiver.writeAsync(message);
                System.out.println("Connected to broker");
                break;
            }
            else
                System.out.println("--No connection--");

        Runnable r = new ReceiverMessageReaderThread(receiver);
        new Thread(r).start();
        System.out.println("--Input \"disconnect\" command to be disconnected from broker--");
        while(true)
        {
            command=buffRead.readLine();
            if(command.equals("disconnect"))
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                s = new Socket("localhost", 1488);
                receiver=new TransportService(s);
                receiver.writeAsync(command+" "+name+"\n");
                break;
            }

        }
    }
}
