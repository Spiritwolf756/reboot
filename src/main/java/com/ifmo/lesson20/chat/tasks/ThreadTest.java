package com.ifmo.lesson20.chat.tasks;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ThreadTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            sleep(1000);
                            System.out.println("Прошло " + j + " сек. в потоке " + currentThread().getName());
                            j++;
                            if (j == 10)
                                currentThread().interrupt();
                        } catch (InterruptedException e) {
                            currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
            if (i == 3) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
