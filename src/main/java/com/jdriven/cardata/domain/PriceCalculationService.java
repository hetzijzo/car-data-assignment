package com.jdriven.cardata.domain;

import com.jdriven.cardata.domain.event.PriceCalculationExpressionUpdatedEvent;

public interface PriceCalculationService {

    void priceCalculationExpressionUpdated(final PriceCalculationExpressionUpdatedEvent priceCalculationExpressionUpdatedEvent);
}
