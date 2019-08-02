package com.jdriven.cardata.domain;

import com.jdriven.cardata.domain.event.RegisteredVehicleImportedEvent;

public interface RegisteredVehicleService {

    void registeredVehicleImported(final RegisteredVehicleImportedEvent registeredVehicleImportedEvent);
}
