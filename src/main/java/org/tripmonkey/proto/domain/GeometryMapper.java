package org.tripmonkey.proto.domain;

import org.tripmonkey.google.places.data.Geometry;

public class GeometryMapper extends ProtoMapper<Geometry, org.tripmonkey.proto.google.places.data.Geometry> {


    @Override
    public org.tripmonkey.proto.google.places.data.Geometry serialize(Geometry d) {
        return org.tripmonkey.proto.google.places.data.Geometry
                .newBuilder().setLocation(placeLocationMapper.serialize(d.getLocation())).build();
    }

    @Override
    public Geometry deserialize(org.tripmonkey.proto.google.places.data.Geometry g) {
        return Geometry.of(placeLocationMapper.deserialize(g.getLocation()));
    }
}
