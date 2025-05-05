package org.tripmonkey.google.places.data;

import jakarta.json.bind.annotation.JsonbProperty;

public class Geometry {

    @JsonbProperty
    PlaceLocation location;

    public PlaceLocation getLocation() {
        return location;
    }

    public static Geometry of(PlaceLocation pl) {
        Geometry g = new Geometry();
        g.location = pl;
        return g;
    }
}
