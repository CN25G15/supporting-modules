package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.domain.location.LocationListHeader;

public final class LocationListHeaderSerde extends
        GRPCSerde<LocationListHeader, org.tripmonkey.patch.contents.data.LocationListHeader> {

    protected LocationListHeaderSerde(){}

    @Override
    protected org.tripmonkey.patch.contents.data.LocationListHeader serialize(LocationListHeader d) {
        return org.tripmonkey.patch.contents.data.LocationListHeader.newBuilder()
                .setUser(userSerde.serialize(d.getUser()))
                .setName(d.getName())
                .build();
    }

    @Override
    protected LocationListHeader deserialize(org.tripmonkey.patch.contents.data.LocationListHeader g) {
        return LocationListHeader.of(g.getName(),userSerde.deserialize(g.getUser()));
    }
}
