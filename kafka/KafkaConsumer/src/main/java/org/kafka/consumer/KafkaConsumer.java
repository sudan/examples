package org.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class KafkaConsumer {

    private static Properties properties = new Properties();
    private static org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;

    public KafkaConsumer() {
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka.consumer.group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "60000");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "10000");
        properties.put("max.poll.records", "1000");
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);
    }

    public static void main(String args[]) {

        new KafkaConsumer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the topic name");
        String topic = scanner.next();

        consumer.subscribe(Arrays.asList(topic));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> partitionRecord : partitionRecords) {
                    System.out.println(partitionRecord.value());
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(partitionRecord.offset() + 1)));
                }
            }
        }
    }
}
