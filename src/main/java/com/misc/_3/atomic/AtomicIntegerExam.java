package com.misc._3.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExam {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int j = 0; j < 10; j++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 10000; k++) {
                        atomicInteger.incrementAndGet();
                    }
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(atomicInteger.get());
    }
}