package demo2;

public class UsingVolatileKeyword  extends Thread{

    private volatile boolean startLoop = true ;

    @Override
    public void run() {

        while (startLoop)
        {
            System.out.println("Inside the Loop " + System.currentTimeMillis());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown(){
        startLoop = false;
    }
}
