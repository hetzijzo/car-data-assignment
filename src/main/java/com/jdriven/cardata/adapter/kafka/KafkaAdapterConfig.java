package com.jdriven.cardata.adapter.kafka;

import com.jdriven.cardata.domain.event.PriceCalculationExpressionUpdatedEvent;
import com.jdriven.cardata.domain.event.RegisteredVehicleImportedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaAdapterConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

//    @Bean
//    ConsumerFactory<String, RegisteredVehicleImportedEvent> consumerFactory() {
//        JsonDeserializer<RegisteredVehicleImportedEvent> deserializer = new JsonDeserializer<>(RegisteredVehicleImportedEvent.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);
//
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
//    }
//
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<String, RegisteredVehicleImportedEvent> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, RegisteredVehicleImportedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

    @Bean
    ProducerFactory<String, RegisteredVehicleImportedEvent> producerFactoryRegisteredVehicleImported() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    KafkaTemplate<String, RegisteredVehicleImportedEvent> kafkaTemplateRegisteredVehicleImported() {
        return new KafkaTemplate<>(producerFactoryRegisteredVehicleImported());
    }

    @Bean
    ProducerFactory<String, PriceCalculationExpressionUpdatedEvent> producerFactoryPriceCalculationExpressionUpdated() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    KafkaTemplate<String, PriceCalculationExpressionUpdatedEvent> kafkaTemplatePriceCalculationExpressionUpdatedEvent() {
        return new KafkaTemplate<>(producerFactoryPriceCalculationExpressionUpdated());
    }
}
