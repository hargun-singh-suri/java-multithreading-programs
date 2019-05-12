package demo5;

public class ThreadPoolExample implements Runnable {
    private int id ;

    public ThreadPoolExample(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Start Task : " + id + " Thread Resposible ===> "+ Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End Task : " + id + " Thread Released ==> "+ Thread.currentThread().getName());
    }
}
