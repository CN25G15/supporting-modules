package org.tripmonkey.google.places.data;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class Place {

    @JsonbProperty String place_id;
    @JsonbProperty Float rating;
    @JsonbProperty List<String> types;
    @JsonbProperty Geometry geometry;

    public static Place of(String place_id, Float rating, List<String> types, Geometry geo) {
        Place p = new Place();
        p.place_id = place_id;
        p.rating = rating;
        p.types = types;
        p.geometry = geo;
        return p;
    }

    public String getPlace_id() {
        return place_id;
    }

    public Float getRating() {
        return rating;
    }

    public List<String> getTypes() {
        return types;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
