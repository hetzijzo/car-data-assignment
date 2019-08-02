package com.jdriven.cardata.adapter.rdw;

import com.jdriven.cardata.domain.RegisteredVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface RdwCarDataMapper {

    @Mapping(target = "brutoBpm", source = "bruto_bpm")
    @Mapping(target = "eersteKleur", source = "eerste_kleur")
    @Mapping(target = "tweedeKleur", source = "tweede_kleur")
    @Mapping(target = "aantalCilinders", source = "aantal_cilinders")
    RegisteredVehicle toRegisteredVehicle(RdwRegisteredVehicle rdwRegisteredVehicle);
}
