package org.tripmonkey.google.places.data;

import jakarta.json.bind.annotation.JsonbProperty;

public class PlaceLocation {

    @JsonbProperty Float lat;
    @JsonbProperty Float lng;

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    public static PlaceLocation from(float latitude, float longitude) {
        PlaceLocation pl = new PlaceLocation();
        pl.lat = latitude;
        pl.lng = longitude;
        return pl;
    }
}
