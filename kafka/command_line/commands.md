# Starting zookeeper

```$xslt
bin/zookeeper-server-start.sh config/zookeeper.properties
```

# Starting broker

```$xslt
bin/kafka-server-start.sh config/server.properties
```

# List all topics

```$xslt
bin/kafka-topics.sh --zookeeper localhost:2181 --list
```

# Create a topic

```$xslt
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic hello_world
```

# Describe a topic

```$xslt
bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic hello_world

Topic:hello_world	PartitionCount:1	ReplicationFactor:1	Configs:
	Topic: hello_world	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
```

# Producer

```$xslt
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic hello_world
```

# Consumer

```$xslt
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic hello_world
```

# Alter topic configs

```$xslt
bin/kafka-topics.sh --alter --zookeeper localhost:2181 --partitions 2 --topic hello_world
```

# Delete a topic

```$xslt
bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic hello_world
```

# Add configurations to kafka topic

```$xslt
bin/kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --entity-name hello_world --alter --add-config max.message.bytes=128000
```

# Remove configuration from kafka topic

```$xslt
bin/kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --entity-name hello_world --alter --delete-config max.message.bytes
```

# Check configuration details for a kafka topic

```$xslt
bin/kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --entity-name hello_world --describe
```
