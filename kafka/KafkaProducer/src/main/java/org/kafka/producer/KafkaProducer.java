package org.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class KafkaProducer {

    private static Properties properties = new Properties();
    private static Producer<String, String> producer;

    public KafkaProducer() {
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("acks", "1");
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        new KafkaProducer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the topic name");
        String topic = scanner.nextLine();

        System.out.println("Enter the messages");
        while (true) {
            String msg = scanner.nextLine();
            producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
            producer.send(record).get();
            producer.close();
        }
    }
}