package org.tripmonkey.domain.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.rest.domain.data.LocationListDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationList {

    @JsonProperty
    String name;
    @JsonProperty List<Location> locations;

    public static LocationList from(String name, List<Location> locations) {
        LocationList ll = new LocationList();
        ll.name = name;
        ll.locations = locations;
        return ll;
    }

    public static LocationList from(LocationListDTO lldto) {
        return from(lldto.getName(), lldto.getLocations().stream().map(Location::from).collect(Collectors.toList()));
    }

    public static LocationList newEmpty(String name) {
        return from(name, new ArrayList<>());
    }

    public static LocationList newSaved() {
        return newEmpty("Saved Locations");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
