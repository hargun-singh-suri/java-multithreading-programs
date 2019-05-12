package demo1.creatingthreadone;

public class WithoutExtendingThread {

    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            System.out.println("ThreadClassOne - " + i);
        }
    }
}
