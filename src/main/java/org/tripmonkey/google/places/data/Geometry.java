package org.tripmonkey.google.places.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

    @JsonProperty
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
