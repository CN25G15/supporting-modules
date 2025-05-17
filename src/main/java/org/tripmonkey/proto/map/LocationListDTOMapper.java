package org.tripmonkey.proto.map;

import org.tripmonkey.proto.contents.data.LocationListDTO;

import java.util.List;
import java.util.Optional;

public class LocationListDTOMapper extends ProtoMapper<org.tripmonkey.rest.domain.data.LocationListDTO, LocationListDTO> {
    
    @Override
    protected LocationListDTO serialize(org.tripmonkey.rest.domain.data.LocationListDTO d) {
        LocationListDTO.Builder b = LocationListDTO.newBuilder();
        if(d.getName() != null){
            b.setName(d.getName());
        }
        if(d.getLocations() != null){
            b.addAllLocations(d.getLocations().stream().map(locationSerde::serialize).toList());
        }
        return b.build();
    }

    @Override
    protected org.tripmonkey.rest.domain.data.LocationListDTO deserialize(LocationListDTO g) {
        return org.tripmonkey.rest.domain.data.LocationListDTO.fromLocList(Optional.ofNullable(g.getName()).orElse(null),
                Optional.ofNullable(g.getLocationsList()).orElse(List.of()).stream().map(locationSerde::deserialize).toList()
                );
    }
}
