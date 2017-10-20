package Broker;
import xPlatform.BrokerService;
import xPlatform.IOperation;
import java.io.IOException;



public class Broker {
    public static void main(String[] args) throws IOException {
        System.out.println("BROKER OPTIONS:");
        IOperation broker=new BrokerService();
        String msg;
        String mesg = broker.deserialzeMessageJDK7("e:\\ca\\message.ser");



            while (true) {
                if (mesg.substring(1,9).equals("message ")) {
                    //result.append(msg.substring(7,msg.length()));
                    System.out.println("We have a message from file:");
                    System.out.println(mesg);
                    broker.writeAsync(mesg.substring(7,mesg.length()));
                }
                while (!(msg = broker.readAsync()).isEmpty())
                    if (msg.equals("invalid")) {
                        System.out.println("--Broker loop--");
                        System.out.println("--INVALID MESSAGE--");
                    } else {
                        System.out.println("--Broker loop--");
                        System.out.println("--VALID MESSAGE--");
                        broker.writeAsync(msg);}
                    }
            }

    }

