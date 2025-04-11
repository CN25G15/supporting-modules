package org.tripmonkey.workspace.domain.location;

import jakarta.json.bind.annotation.JsonbProperty;

// ADD
public class LocationDataWrapper {

    @JsonbProperty Location location;
    @JsonbProperty LocationData locationData;

    public Location getLocation() {
        return location;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public static LocationDataWrapper of(Location loc, LocationData locdata){
        LocationDataWrapper ldw = new LocationDataWrapper();
        ldw.location = loc;
        ldw.locationData = locdata;
        return ldw;
    }
}
