package java_Practice.spring_test_demo.practice;//package practice;
//
//class Counter {
//    int count = 0;
//
//    public void increment() {
//        count++; // Not thread-safe
//    }
//}
//
//public class Main {
//    public static void main(String[] args) throws InterruptedException {
//        Counter c = new Counter();
//
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 1000; i++) c.increment();
//        });
//
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 1000; i++) c.increment();
//        });
//
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//
//        System.out.println("Final Count: " + c.count); // May be < 2000
//    }
//}
