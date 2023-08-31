package ru.neoflex.dossier.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.JacksonUtils;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConfigConsumer {
    @Bean
    public ObjectMapper objectMapper(){
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper){
        log.trace("KafkaConfigProducer.producerFactory - KafkaProperties{}, ObjectMapper {}", kafkaProperties, objectMapper);

        Map<String, Object> props = kafkaProperties.buildConsumerProperties();

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        //props.put(JsonDeserializer.TRUSTED_PACKAGES, "ru.neoflex.dossier.dto.EmailMessage");
        //props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, EmailMessage.class.getName());
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        DefaultKafkaConsumerFactory<String,String> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(props);
        log.trace("KafkaConfig.producerFactory - DefaultKafkaProducerFactory{}",kafkaConsumerFactory);
        return kafkaConsumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties,objectMapper));
        return factory;
    }
}