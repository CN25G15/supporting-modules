package org.tripmonkey.google.places.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty
    String place_id;
    @JsonProperty Float rating;
    @JsonProperty List<String> types;
    @JsonProperty Geometry geometry;
    @JsonProperty String name;

    public static Place of(String place_id, String name, Float rating, List<String> types, Geometry geo) {
        Place p = new Place();
        p.place_id = place_id;
        p.name = name;
        p.rating = rating;
        p.types = types;
        p.geometry = geo;
        return p;
    }

    public static Place of(String place_id){
        return of(place_id, "Unknown", 0.0F, List.of("Unknown"), null);
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

    public String getName() { return name; }
}
