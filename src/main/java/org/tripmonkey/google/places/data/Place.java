package org.tripmonkey.google.places.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonbProperty String place_id;
    @JsonbProperty Float rating;
    @JsonbProperty List<String> types;

    public static Place of(String place_id, Float rating, List<String> types) {
        Place p = new Place();
        p.place_id = place_id;
        p.rating = rating;
        p.types = types;
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
}
