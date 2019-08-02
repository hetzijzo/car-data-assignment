package com.jdriven.cardata.adapter.rdw;

import com.jdriven.cardata.domain.RegisteredVehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RdwRegisteredVehicleTest {

    @Autowired
    private RdwCarDataMapper mapper;

    @Test
    public void testMapper() {
        RdwRegisteredVehicle rdwRegisteredVehicle = RdwRegisteredVehicle.builder()
            .merk("Toyota")
            .build();

        RegisteredVehicle registeredVehicle = mapper.toRegisteredVehicle(rdwRegisteredVehicle);
        assertThat(registeredVehicle.getMerk()).isEqualTo("Toyota");
    }

}
