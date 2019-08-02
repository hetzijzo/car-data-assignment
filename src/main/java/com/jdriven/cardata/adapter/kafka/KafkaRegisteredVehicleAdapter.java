package com.jdriven.cardata.adapter.kafka;

import com.jdriven.cardata.domain.event.RegisteredVehicleImportedEvent;
import com.jdriven.cardata.domain.RegisteredVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
class KafkaRegisteredVehicleAdapter implements RegisteredVehicleService {

    private final KafkaTemplate<String, RegisteredVehicleImportedEvent> kafkaTemplate;

    @Async
    @EventListener
    @Override
    public void registeredVehicleImported(RegisteredVehicleImportedEvent registeredVehicleImportedEvent) {
        try {
            Thread.sleep(new Random().nextInt((10000 - 2000) + 1) + 2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.kafkaTemplate.send(
            MessageBuilder.withPayload(registeredVehicleImportedEvent)
                .setHeader(KafkaHeaders.TOPIC, "registered-vehicles")
                .build())
            .addCallback(successMessage -> log.info("Send RegisteredVehicleImportedEvent for {}", registeredVehicleImportedEvent.getRegisteredVehicle().getId()),
                ex -> log.error("Exception during send RegisteredVehicleImportedEvent", ex));
    }
}
