package com.hiruna.contract.kafka.producer;

import com.hiruna.contract.data.dto.CustomerNotifDTO;
import com.hiruna.contract.data.dto.WorkerNotifDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotifProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String server_addr;

    //for sending  customer notifications
    @Bean
    public ProducerFactory<String, CustomerNotifDTO> customerNotifProducerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server_addr);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, CustomerNotifDTO> customerNotifKafkaTemplate(){
        return new KafkaTemplate<>(customerNotifProducerFactory());
    }

    //for sending worker notification
    @Bean
    public ProducerFactory<String, WorkerNotifDTO> workerNotifProductionFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server_addr);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, WorkerNotifDTO> workerNotifKafkaTemplate(){
        return new KafkaTemplate<>(workerNotifProductionFactory());
    }
}
