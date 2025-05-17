package org.tripmonkey.proto.map;

import org.tripmonkey.google.places.data.Place;

public class PlaceMapper extends ProtoMapper<Place, org.tripmonkey.proto.google.places.data.Place> {
    @Override
    protected org.tripmonkey.proto.google.places.data.Place serialize(Place d) {
        return org.tripmonkey.proto.google.places.data.Place.newBuilder()
                .setPlaceId(d.getPlace_id()).setRating(d.getRating())
                .setGeometry(geometryMapper.serialize(d.getGeometry()))
                .addAllType(d.getTypes()).build();
    }

    @Override
    protected Place deserialize(org.tripmonkey.proto.google.places.data.Place g) {
        return Place.of(g.getPlaceId(),g.getRating(),g.getTypeList().stream().toList(),geometryMapper.deserialize(g.getGeometry()));
    }
}
