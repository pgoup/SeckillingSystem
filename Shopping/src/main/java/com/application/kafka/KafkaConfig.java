package com.application.kafka;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig
{
    @Bean
    public ProducerFactory<String,String> producerFactory()
    {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public Map<String,Object> producerConfig()
    {
        Map<String,Object> props=new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.2.2:9092");
        props.put(ProducerConfig.RETRIES_CONFIG,0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,16834);
        props.put(ProducerConfig.LINGER_MS_CONFIG,1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String ,String> kafkaTemplate()
    {
        return new KafkaTemplate<String,String>  (producerFactory());
    }
}