package com.safa;

public class Main {

    public static void main(String[] args) {

        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                producerConsumer.producer();
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                producerConsumer.consume();
            }
        });


        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
