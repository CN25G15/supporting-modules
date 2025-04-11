package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.domain.location.Location;

public final class LocationSerde extends
        GRPCSerde<Location, org.tripmonkey.patch.contents.data.Location> {

    protected LocationSerde(){}

    @Override
    public org.tripmonkey.patch.contents.data.Location serialize(Location d) {
        return org.tripmonkey.patch.contents.data.Location.newBuilder()
                .setUuid(d.toString()).build();
    }

    @Override
    public Location deserialize(org.tripmonkey.patch.contents.data.Location g) {
        return Location.from(g.getUuid());
    }
}
