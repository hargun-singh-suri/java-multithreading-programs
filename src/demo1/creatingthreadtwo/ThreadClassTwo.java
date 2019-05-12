package demo1.creatingthreadtwo;

public class ThreadClassTwo implements Runnable{
    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            System.out.println("ThreadClassOne - " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
