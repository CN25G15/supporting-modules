package org.tripmonkey.google.places;

import org.tripmonkey.google.places.data.Place;
import org.tripmonkey.proto.ProtoSerde;

public class PlaceSerde extends ProtoSerde<Place, org.tripmonkey.proto.google.places.data.Place> {

    public PlaceSerde(){}

    @Override
    protected org.tripmonkey.proto.google.places.data.Place serialize(Place d) {
        return org.tripmonkey.proto.google.places.data.Place.newBuilder()
                .setPlaceId(d.getPlace_id())
                .setRating(d.getRating())
                .addAllType(d.getTypes())
                .build();
    }

    @Override
    protected Place deserialize(org.tripmonkey.proto.google.places.data.Place g) {
        return Place.of(
                g.getPlaceId(),
                g.getRating(),
                g.getTypeList().stream().toList()
        );
    }
}
