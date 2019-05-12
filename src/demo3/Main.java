package demo3;

public class Main {
    private int count = 0;

    public synchronized void incrementCount(){

        count++;
    }

    public static void main(String args []){

        Main obj = new Main();
        obj.test();
    }

    public  void test(){
        Thread obj1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementCount();
            }
        });

        Thread obj2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementCount();
            }
        });

        obj1.start();
        obj2.start();
        try {
            obj1.join();
            obj2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(count);
    }
}
