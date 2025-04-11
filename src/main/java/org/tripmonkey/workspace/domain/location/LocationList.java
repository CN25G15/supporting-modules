package org.tripmonkey.workspace.domain.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocationList {

    @JsonbProperty UUID id;
    @JsonbProperty String name;
    @JsonbProperty User creator;
    @JsonbProperty List<Location> entries;

    private static LocationList newList(String name, User creator){
        LocationList ll = new LocationList();
        ll.id = UUID.randomUUID();
        ll.name = name;
        ll.creator = creator;
        ll.entries = new ArrayList<>();
        return ll;
    }

    public static LocationList emptySaved(User creator){
        return newList("Saved Places",creator);
    }

    public static LocationList from(LocationListHeader llh) {
        return newList(llh.getName(),llh.getUser());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return creator;
    }

    public List<Location> getEntries() {
        return entries;
    }
}
