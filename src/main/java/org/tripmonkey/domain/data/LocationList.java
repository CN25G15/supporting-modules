package org.tripmonkey.domain.data;


import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.rest.domain.data.LocationListDTO;

import java.util.List;

public class LocationList {

    @JsonbProperty String name;
    @JsonbProperty List<Location> locations;

    public static LocationList from(String name, List<Location> locations) {
        LocationList ll = new LocationList();
        ll.name = name;
        ll.locations = locations;
        return ll;
    }

    public static LocationList from(LocationListDTO lldto) {
        return from(lldto.getName(), lldto.getLocations().stream().map(Location::from).toList());
    }

    public static LocationList newEmpty(String name) {
        return from(name, List.of());
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
