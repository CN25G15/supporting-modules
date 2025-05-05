package org.tripmonkey.proto.domain;

import org.tripmonkey.domain.data.Location;

public class LocationMapper extends ProtoMapper<Location, org.tripmonkey.workspace.service.Location> {

    @Override
    protected org.tripmonkey.workspace.service.Location serialize(Location d) {
        return org.tripmonkey.workspace.service.Location.newBuilder()
                .setPlaceId(d.getPlaceId())
                .build();
    }

    @Override
    protected Location deserialize(org.tripmonkey.workspace.service.Location g) {
        return Location.from(g.getPlaceId());
    }
}
