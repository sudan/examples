package org.kafka.stream;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KafkaStream {

    private static Properties properties = new Properties();
    private static Serde<String> stringSerde = Serdes.String();
    private static Serde<Long> longSerde = Serdes.Long();

    public KafkaStream() {
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, stringSerde.getClass().getName());
        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, longSerde.getClass().getName());
    }

    public static void main(String args[]) {

        new KafkaStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the topic:");
        String topic = scanner.next();

        KStreamBuilder builder = new KStreamBuilder();
        KStream<String, String> textLines = builder.stream(stringSerde, stringSerde, topic);
        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
        KStream<String, Long> wordCounts = textLines
                .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
                .map((key, word) -> new KeyValue<>(word, word))
                .countByKey("Counts")
                .toStream();


        wordCounts.to(stringSerde, longSerde, "wordcounts");
        KafkaStreams kafkaStreams = new KafkaStreams(builder, properties);
        kafkaStreams.start();
    }
}