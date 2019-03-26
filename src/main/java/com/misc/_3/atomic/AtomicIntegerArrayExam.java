package com.misc._3.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayExam {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray array = new AtomicIntegerArray(5);
        array.set(0, 0);
        array.set(1, 0);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        array.getAndIncrement(0);
                    }
                    for (int j = 0; j < 10000; j++) {
                        array.getAndIncrement(1);
                    }
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("array[0] = " + array.get(0) + ", array[1] = " + array.get(1));
        System.out.println("array = " + array.toString());
    }
}