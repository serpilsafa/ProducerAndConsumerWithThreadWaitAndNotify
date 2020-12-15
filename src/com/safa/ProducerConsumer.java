package com.safa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {
    private Random random = new Random();
    private Object lock = new Object();
    private Queue<Integer> queue = new LinkedList<>();
    private int limit = 10;


    public void producer(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock){
                if(queue.size() == limit){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Integer value = random.nextInt(100);
                queue.offer(value);
                System.out.println("Producer is creating: "+ value);
                lock.notify();
            }


        }
    }

    public void consume(){
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock){
                if(queue.size() == 0){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Integer value = queue.poll();
                System.out.println("Consumer is consuming: "+ value);
                System.out.println("Queue size: "+ queue.size());
                lock.notify();
            }
        }
    }
}
