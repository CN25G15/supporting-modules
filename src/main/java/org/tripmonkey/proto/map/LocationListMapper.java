package org.tripmonkey.proto.map;

import org.tripmonkey.domain.data.LocationList;

public class LocationListMapper extends ProtoMapper<LocationList, org.tripmonkey.workspace.service.LocationList> {

    @Override
    protected org.tripmonkey.workspace.service.LocationList serialize(LocationList d) {
        return org.tripmonkey.workspace.service.LocationList.newBuilder()
                .setName(d.getName())
                .addAllLocations(d.getLocations().stream().map(locationMapper::serialize).toList())
                .build();
    }

    @Override
    protected LocationList deserialize(org.tripmonkey.workspace.service.LocationList g) {
        return LocationList.from(g.getName(), g.getLocationsList().stream().map(locationMapper::deserialize).toList());
    }
}
