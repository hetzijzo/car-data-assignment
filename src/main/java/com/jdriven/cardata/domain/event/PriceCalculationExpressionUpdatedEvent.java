package com.jdriven.cardata.domain.event;

import com.jdriven.cardata.domain.PriceCalculationExpression;
import com.jdriven.cardata.domain.RegisteredVehicle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceCalculationExpressionUpdatedEvent {

    private final PriceCalculationExpression priceCalculationExpression;

    public static PriceCalculationExpressionUpdatedEvent forPriceCalculationExpression(PriceCalculationExpression priceCalculationExpression) {
        return PriceCalculationExpressionUpdatedEvent.builder()
            .priceCalculationExpression(priceCalculationExpression)
            .build();
    }


}
