package com.jdriven.cardata.domain.event;

import com.jdriven.cardata.domain.RegisteredVehicle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegisteredVehicleImportedEvent {

    private final RegisteredVehicle registeredVehicle;

    public static RegisteredVehicleImportedEvent forRegisteredVehicle(RegisteredVehicle registeredVehicle) {
        return RegisteredVehicleImportedEvent.builder()
            .registeredVehicle(registeredVehicle)
            .build();
    }
}
