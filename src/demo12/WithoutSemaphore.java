package demo12;

public class WithoutSemaphore {

    private int connections = 0;
    private static WithoutSemaphore singletonObject = new WithoutSemaphore();
    //No new can be performed on it
    private WithoutSemaphore() {
    }

    public static WithoutSemaphore getInstance(){
        return singletonObject;
    }

    public void connect()  {
        //Increment the no of connections
        synchronized (this){
            connections ++;
            System.out.println("Total no of Connections Created " + connections + " ; Current Thread "+ Thread.currentThread().getName()  );
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
            System.out.println("Total no of Connections after release ;" + connections + " ; Current Thread "+ Thread.currentThread().getName()  );
        }

    }
}
