package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.domain.location.LocationDataWrapper;

public final class LocationDataWrapperSerde extends GRPCSerde<LocationDataWrapper, org.tripmonkey.patch.contents.data.LocationDataWrapper> {

    protected LocationDataWrapperSerde(){}

    @Override
    protected org.tripmonkey.patch.contents.data.LocationDataWrapper serialize(LocationDataWrapper d) {
        return org.tripmonkey.patch.contents.data.LocationDataWrapper.newBuilder()
                .setLocation(locationSerde.serialize(d.getLocation()))
                .setLocData(locationDataSerde.serialize(d.getLocationData()))
                .build();
    }

    @Override
    protected LocationDataWrapper deserialize(org.tripmonkey.patch.contents.data.LocationDataWrapper g) {
        return LocationDataWrapper.of(
                locationSerde.deserialize(g.getLocation()),
                locationDataSerde.deserialize(g.getLocData())
        );
    }
}
