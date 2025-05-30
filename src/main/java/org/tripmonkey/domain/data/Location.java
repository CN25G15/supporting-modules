package org.tripmonkey.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.rest.domain.data.LocationDTO;

public class Location {

    @JsonProperty
    private String place_id;


    public static Location from(String place_id) {
        Location l = new Location();
        l.place_id = place_id;
        return l;
    }

    public String getPlaceId() {
        return place_id;
    }

    public static Location from(LocationDTO ldto) {
        return from(ldto.getPlace_id());
    }
}
