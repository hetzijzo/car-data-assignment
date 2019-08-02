package com.jdriven.cardata.adapter.rdw;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class RdwRegisteredVehicle {

    private final String kenteken;
    private final String merk;
    private final String handelsbenaming;
    private final String type;
    private final String variant;
    private final String uitvoering;
    private final String voertuigsoort;

    @Builder.Default
    private final Double catalogusprijs = -1.0;
    @Builder.Default
    private final Double bruto_bpm = -1.0;

    private final String eerste_kleur;
    private final String tweede_kleur;
    private final Integer aantal_cilinders;

    @Builder.Default
    private final Integer cilinderinhoud = -1;

}
