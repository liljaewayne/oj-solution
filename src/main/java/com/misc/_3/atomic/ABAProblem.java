package com.misc._3.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ABAProblem {
    public static void main(String[] args) throws InterruptedException {
        MyStack<String> stack = new MyStack<>();
        stack.push("B");
        stack.push("A");
        System.out.println("Stack init:" + stack);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread1");
                stack.pop();
                System.out.println("Thread1 pop :" + stack);
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread2");
                Node<String> A = stack.pop();
                System.out.println("Thread2 pop :" + stack);
                stack.pop();
                System.out.println("Thread2 pop :" + stack);
                stack.push("D");
                System.out.println("Thread2 push D:" + stack);
                stack.push("C");
                System.out.println("Thread2 push C:" + stack);
                stack.push(A);
                System.out.println("Thread2 push A:" + stack);
            }
        });
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Stack result:" + stack);
    }

    static class MyStack<T> {
        AtomicReference<Node<T>> head = new AtomicReference<>(null);

        public void push(T value) {
            Node<T> node = new Node<>(value);
            push(node);
        }

        public void push(Node<T> node) {
            for (; ; ) {
                Node<T> tmpHead = head.get();
                if (head.compareAndSet(tmpHead, node)) {
                    node.setNext(tmpHead);
                    return;
                }
            }
        }

        public Node<T> pop() {
            for (; ; ) {
                Node<T> node = head.get();
                if (node == null) {
                    return null;
                }
                Node<T> nextNode = node.getNext();
                // add this sleep to cause ABA problem
                if (Thread.currentThread().getName().equals("Thread1")) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (head.compareAndSet(node, nextNode)) {
                    return node;
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            Node<T> node = head.get();
            while (node != null) {
                sb.append(node.getValue());
                if (node.getNext() != null) {
                    sb.append(",");
                }
                node = node.getNext();
            }
            sb.append("]");
            return sb.toString();
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}