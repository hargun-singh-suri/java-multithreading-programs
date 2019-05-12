package demo3;

public class WithoutSyncronize extends Thread {
    private static int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }
}
