package task2;

import java.util.concurrent.atomic.AtomicBoolean;

public class SearchingThread extends Thread {
    private int number;
    private AtomicBoolean isDone = new AtomicBoolean(true);
    private SearchingProcessor processor;

    public SearchingThread(SearchingProcessor processor) {

        this.processor = processor;
    }

    public void process(int number) {
        this.number = number;
        isDone.set(false);
    }

    public boolean getIsDone() {

        return isDone.get();
    }

    @Override
    public void run() {
        while(true) {

            if (isDone.get()) {
                continue;
            }

            processor.process(number);

            isDone.set(true);
        }

    }
}