package org.tripmonkey.workspace.data.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.workspace.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocationList {

    @JsonProperty UUID id;
    @JsonProperty String name;
    @JsonProperty User creator;
    @JsonProperty List<Location> entries;

    public static LocationList newList(String name, User creator){
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
