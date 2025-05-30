package org.tripmonkey.google.places.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceLocation {

    @JsonProperty
    Float lat;
    @JsonProperty Float lng;

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
