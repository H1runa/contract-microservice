package com.hiruna.contract.kafka.topic;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String server_addr;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, server_addr);
        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic customer_notif_topic(){
        return new NewTopic("customer-notification", 1, (short) 1);
    }

    @Bean
    public NewTopic worker_notif_topic(){
        return new NewTopic("worker-notification", 1, (short) 1);
    }

}
