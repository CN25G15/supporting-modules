package org.tripmonkey.proto.mapper;

import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;

import java.util.List;
import java.util.Optional;

public class LocationListDTOMapper extends ProtoSerde<LocationListDTO, org.tripmonkey.proto.contents.data.LocationListDTO> {
    
    @Override
    protected org.tripmonkey.proto.contents.data.LocationListDTO serialize(LocationListDTO d) {
        org.tripmonkey.proto.contents.data.LocationListDTO.Builder b = org.tripmonkey.proto.contents.data.LocationListDTO.newBuilder();
        if(d.getName() != null){
            b.setName(d.getName());
        }
        if(d.getLocations() != null){
            b.addAllLocations(d.getLocations().stream().map(locationSerde::serialize).toList());
        }
        return b.build();
    }

    @Override
    protected LocationListDTO deserialize(org.tripmonkey.proto.contents.data.LocationListDTO g) {
        return LocationListDTO.fromLocList(Optional.ofNullable(g.getName()).orElse(null),
                Optional.ofNullable(g.getLocationsList()).orElse(List.of()).stream().map(locationSerde::deserialize).toList()
                );
    }
}
