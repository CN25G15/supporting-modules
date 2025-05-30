package org.tripmonkey.rest.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

    @JsonProperty
    String place_id;

    public String getPlace_id() {
        return place_id;
    }

    public static LocationDTO from(String place_id){
        LocationDTO l = new LocationDTO();
        l.place_id = place_id;
        return l;
    }
}
