package demo11;

public class Main {

    public  static void main(String args[]) throws InterruptedException {

        Processor processor = new Processor();

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.threadOneProcessor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.threadTwoProcessor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        processor.finished();

    }
}
