package com.panaroma.kafkaproducer.config;

import com.panaroma.kafkaproducer.consts.MessageKey;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partitionCount}")
    private Integer partitionCount;

    @Value("${topic.replication}")
    private Integer replicationCount;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .partitions(partitionCount)
                .replicas(replicationCount)
                .build();
    }

    @KafkaListener(id = MessageKey.NAME_KEY, topics = "student_name")
    public void studentNameListener(String in) {
        System.out.println(in);
    }

}
