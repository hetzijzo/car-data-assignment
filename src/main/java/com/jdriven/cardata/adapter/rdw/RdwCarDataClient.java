package com.jdriven.cardata.adapter.rdw;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rdw", url = "https://opendata.rdw.nl")
interface RdwCarDataClient {

    @GetMapping("/resource/m9d7-ebf2.json")
    List<RdwRegisteredVehicle> getRegisteredVehicles();
}
