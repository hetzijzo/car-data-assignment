package com.jdriven.cardata.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceCalculationExpression {

    private final UUID registeredVehicleId;
    private final String calculationExpression;

    public static PriceCalculationExpression create(UUID registeredVehicleId, String calculationExpression) {
        return PriceCalculationExpression.builder()
            .registeredVehicleId(registeredVehicleId)
            .calculationExpression(calculationExpression)
            .build();
    }
}
