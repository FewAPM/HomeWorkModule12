package task1;

import static java.lang.Thread.currentThread;

public class Task1 {


    public static void main(String[] args) throws InterruptedException {

        Thread inOneSecond = new Thread(()-> {
            long startTime = System.currentTimeMillis();
            while (!currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000L);
                    long timePassed = System.currentTimeMillis() - startTime;
                    System.out.println("timePassed = " + timePassed + " ms");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread inFiveSeconds = new Thread(()-> {
            while (!currentThread().isInterrupted()) {
                try {
                    Thread.sleep(5000L);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        inOneSecond.start();
        inFiveSeconds.start();


    }
}
