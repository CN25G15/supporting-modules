package org.tripmonkey.rest.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LocationListDTO {

    @JsonProperty
    String name;
    @JsonProperty List<LocationDTO> locations;

    public String getName() {
        return name;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public static LocationListDTO fromLocList(String name, List<LocationDTO> locations) {
        LocationListDTO ll = new LocationListDTO();
        ll.name = name;
        ll.locations = locations;
        return ll;
    }

    public static LocationListDTO fromStringList(String name, List<String> locations) {
        return fromLocList(name, locations.stream().map(LocationDTO::from).toList());
    }

}
