package com.misc._3.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AtomReferenceExam {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Element> reference = new AtomicReference<>(new Element(0, 0));

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        // 自旋锁写法, 在使用AtomicReference时经常使用
                        boolean flag = false;
                        while (!flag) {
                            Element storedElement = reference.get();
                            Element newElement = new Element(storedElement.x + 1, storedElement.y + 1);
                            flag = reference.compareAndSet(storedElement, newElement);
                        }
                    }
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("element.x=" + reference.get().x + ",element.y=" + reference.get().y);
    }

    private static class Element {
        int x;
        int y;

        public Element(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}