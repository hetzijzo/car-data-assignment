package com.jdriven.cardata.adapter.kafka;

import com.jdriven.cardata.domain.PriceCalculationService;
import com.jdriven.cardata.domain.event.PriceCalculationExpressionUpdatedEvent;
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
public class KafkaPriceCalculationAdapter implements PriceCalculationService {

    private final KafkaTemplate<String, PriceCalculationExpressionUpdatedEvent> kafkaTemplate;

    @Async
    @EventListener
    @Override
    public void priceCalculationExpressionUpdated(PriceCalculationExpressionUpdatedEvent priceCalculationExpressionUpdatedEvent) {
        try {
            Thread.sleep(new Random().nextInt((10000 - 2000) + 1) + 2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.kafkaTemplate.send(
            MessageBuilder.withPayload(priceCalculationExpressionUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "price-calculation-expressions")
                .build())
            .addCallback(successMessage -> log.info("Send PriceCalculationExpressionUpdatedEvent for {}", priceCalculationExpressionUpdatedEvent.getPriceCalculationExpression().getRegisteredVehicleId()),
                ex -> log.error("Exception during send PriceCalculationExpressionUpdatedEvent", ex));
    }
}
