package com.jdriven.cardata.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisteredVehicleImportCLR implements CommandLineRunner {

    private final RegisteredVehicleRepository registeredVehicleRepository;

    @Override
    public void run(String... args) {
        this.registeredVehicleRepository.getRegisteredVehicles()
            .stream()
            .limit(100)
            .forEach(registeredVehicle ->{
                registeredVehicle.importRegisteredVehicle();
                registeredVehicle.determinePriceCalculationExpression();
            });
    }
}
