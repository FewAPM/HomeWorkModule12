package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Task2 {
    public static void main(String[] args) {
        List<String> result = new CopyOnWriteArrayList<>();

        int inputNumber = 15;

        SearchingThread fizzBuzz = new SearchingThread((n) -> {
            if (n%15 == 0) {
                result.add("FizzBuzz");
            }
        });

        SearchingThread fizz = new SearchingThread((n) -> {
            if (n%3 == 0 && n%5 != 0) {
                result.add("Fizz");

            }
        });

        SearchingThread buzz = new SearchingThread((n) -> {
            if(n%5 == 0 && n%3 != 0) {
                result.add("Buzz");
            }
        });

        SearchingThread number = new SearchingThread((n) -> {
            if (n%3 != 0 && n%5 != 0) {
                result.add(String.valueOf(n));
            }
        });

        List<SearchingThread> threads = new ArrayList<>();
        threads.add(fizzBuzz);
        threads.add(fizz);
        threads.add(buzz);
        threads.add(number);


        fizzBuzz(result, inputNumber, threads);
    }

    private static void fizzBuzz(List<String> result, int inputNumber, List<SearchingThread> threads) {
        for (SearchingThread thread : threads) {
            thread.start();
        }

        for (int i = 1; i <= inputNumber; i++) {
            for (SearchingThread thread : threads) {
                thread.process(i);

            }

            while (true) {
                int processedCount = 0;
                for (SearchingThread thread : threads) {
                    if (thread.getIsDone()) {
                        processedCount++;
                    }
                }
                if (processedCount == threads.size()) {
                    break;
                }
            }
        }

        Object[] objects = result.toArray();
        String print = Arrays.toString(objects);
        print = print.substring(1, print.length() - 1);
        System.out.println(print);
    }
}