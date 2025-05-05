package org.tripmonkey.proto.domain;

import org.tripmonkey.proto.contents.data.LocationDTO;

public class LocationDTOMapper extends ProtoMapper<org.tripmonkey.rest.domain.data.LocationDTO, LocationDTO> {
    @Override
    protected LocationDTO serialize(org.tripmonkey.rest.domain.data.LocationDTO d) {
        return LocationDTO.newBuilder()
                .setPlaceId(d.getPlace_id())
                .build();
    }

    @Override
    protected org.tripmonkey.rest.domain.data.LocationDTO deserialize(LocationDTO g) {
        return org.tripmonkey.rest.domain.data.LocationDTO.from(g.getPlaceId());
    }
}
