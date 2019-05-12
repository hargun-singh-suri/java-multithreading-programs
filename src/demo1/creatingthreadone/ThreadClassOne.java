package demo1.creatingthreadone;

public class ThreadClassOne extends Thread {

    //Extending the Thread class and we will override the method of the Thread class that is Run() this will have the code which we want to execute
    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            System.out.println("ThreadClassOne - " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
