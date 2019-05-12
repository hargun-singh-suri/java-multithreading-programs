package demo3;

public class WithSyncronize extends Thread {

//Static variable means shared among the objects
    private static int count = 0;

    public synchronized void Increment(){
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            Increment();
        }
    }

}
