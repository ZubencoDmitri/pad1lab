package xPlatform;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;


public class TransportService implements IOperation{
    Socket transport;                           //127.0.0.1 port 1488
    public TransportService(Socket transport) {
        this.transport = transport;
    }

    @Override
    public String readAsync() {
        ExecutorService executor = Executors.newSingleThreadExecutor(); //  Thread Executor
        Callable<String> task = new Callable<String>() {
            public String call() {
                InputStream istream = null;
                String partlyTransData;
                StringBuffer result = new StringBuffer();
                try {
                    istream = transport.getInputStream();
                    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
                    if (!(partlyTransData = receiveRead.readLine()).isEmpty())
                        result.append(partlyTransData.trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result.toString();
            }
        };
        Future<String> future = executor.submit(task);
        String message="";
        while(!future.isDone());
        try{
            message=future.get();
        } catch (InterruptedException ie) {
            ie.printStackTrace(System.err);
        } catch (ExecutionException ee) {
            ee.printStackTrace(System.err);
        }
        executor.shutdown();
        return message;
    }
    @Override
    public void writeAsync(String message) {
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream ostream = null;
                try {
                    ostream = transport.getOutputStream();
                    PrintWriter pwrite = new PrintWriter(ostream, true);
                    pwrite.println(message);    // sending to server
                    pwrite.flush();             // flush the data
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public String deserialzeMessageJDK7(String filename){
        return null;
    };
}
