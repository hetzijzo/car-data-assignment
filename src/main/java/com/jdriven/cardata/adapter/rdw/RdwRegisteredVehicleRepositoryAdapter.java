package com.jdriven.cardata.adapter.rdw;

import com.jdriven.cardata.domain.RegisteredVehicle;
import com.jdriven.cardata.domain.RegisteredVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class RdwRegisteredVehicleRepositoryAdapter implements RegisteredVehicleRepository {

    private final RdwCarDataClient rdwCarDataClient;
    private final RdwCarDataMapper rdwCarDataMapper;

    @Override
    public List<RegisteredVehicle> getRegisteredVehicles() {
        return rdwCarDataClient.getRegisteredVehicles()
            .stream()
            .map(rdwCarDataMapper::toRegisteredVehicle)
            .collect(Collectors.toList());
    }
}
