package org.tripmonkey.proto.map;

import org.tripmonkey.google.places.data.PlaceLocation;

public class PlaceLocationMapper extends ProtoMapper<PlaceLocation, org.tripmonkey.proto.google.places.data.PlaceLocation> {
    @Override
    protected org.tripmonkey.proto.google.places.data.PlaceLocation serialize(PlaceLocation d) {
        return org.tripmonkey.proto.google.places.data.PlaceLocation
                .newBuilder().setLat(d.getLat()).setLng(d.getLng()).build();
    }

    @Override
    protected PlaceLocation deserialize(org.tripmonkey.proto.google.places.data.PlaceLocation g) {
        return PlaceLocation.from(g.getLat(),g.getLng());
    }
}
