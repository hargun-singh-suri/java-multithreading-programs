package demo12;

import java.util.concurrent.Semaphore;

public class WithSemaphore {


    private int connections = 0;
    private static WithSemaphore singletonObject = new WithSemaphore();

    //Total no of connections which need to be maintained
    Semaphore semaphore = new Semaphore(10);


    //No new can be performed on it
    private WithSemaphore() {
    }

    public static WithSemaphore getInstance(){
        return singletonObject;
    }

    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    public void doConnect() {

        //Increment the no of connections
        synchronized (this){
            connections ++;
            System.out.println("Total no of Connections Created " + connections);
        }
        //Performing some virtual operation meanwhile all the Connection will be utilized
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Release the connections
        synchronized (this){
            connections --;
            System.out.println("Total no of Connections after releasing - "+Thread.currentThread().getName() +" = "+ + connections);
        }

    }
}
