package org.tripmonkey.proto.mapper;

import org.tripmonkey.rest.domain.data.LocationDTO;

public class LocationDTOMapper extends ProtoSerde<LocationDTO, org.tripmonkey.proto.contents.data.LocationDTO> {
    @Override
    protected org.tripmonkey.proto.contents.data.LocationDTO serialize(LocationDTO d) {
        return org.tripmonkey.proto.contents.data.LocationDTO.newBuilder()
                .setPlaceId(d.getPlace_id())
                .build();
    }

    @Override
    protected LocationDTO deserialize(org.tripmonkey.proto.contents.data.LocationDTO g) {
        return LocationDTO.from(g.getPlaceId());
    }
}
