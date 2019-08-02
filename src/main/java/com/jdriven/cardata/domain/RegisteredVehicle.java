package com.jdriven.cardata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdriven.cardata.domain.event.PriceCalculationExpressionUpdatedEvent;
import com.jdriven.cardata.domain.event.RegisteredVehicleImportedEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Configurable
@Slf4j
public final class RegisteredVehicle {

    @Builder.Default
    private final UUID id = UUID.randomUUID();

    private final String kenteken;
    private final String merk;
    private final String handelsbenaming;
    private final String type;
    private final String variant;
    private final String uitvoering;
    private final String voertuigsoort;

    private final Double catalogusprijs;
    private final Double brutoBpm;
    private final String eersteKleur;
    private final String tweedeKleur;
    private final Integer aantalCilinders;
    private final Integer cilinderinhoud;

    @Autowired
    @JsonIgnore
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    @JsonIgnore
    private ExpressionParser expressionParser;

    public void importRegisteredVehicle() {
        applicationEventPublisher.publishEvent(RegisteredVehicleImportedEvent.forRegisteredVehicle(this));
    }

    public void determinePriceCalculationExpression() {
        PriceCalculationExpression priceCalculationExpression;
        try {
            Expression expression = expressionParser.parseExpression("#registeredVehicle.getCatalogusprijs() != null ? #registeredVehicle.getCatalogusprijs() / 4 : 0");
            priceCalculationExpression = PriceCalculationExpression.create(getId(), expression.getExpressionString());

            EvaluationContext context = new StandardEvaluationContext();
            context.setVariable("registeredVehicle", this);
            Double price = expression.getValue(context, Double.class);

            if (price != null && price > 0.0) {
                applicationEventPublisher.publishEvent(PriceCalculationExpressionUpdatedEvent.forPriceCalculationExpression(priceCalculationExpression));
            }
        } catch (Exception ex) {
            log.error("Could not create PriceCalculationExpression correctly", ex);
        }
    }
}
