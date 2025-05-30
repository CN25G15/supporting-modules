package org.tripmonkey.domain.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.domain.patch.PatchVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Workspace {

    @JsonProperty String wid;
    @JsonProperty List<User> collaborators;
    @JsonProperty List<LocationList> locationLists;
    @JsonIgnore Map<Location, LocationMetadata> locationData; //TODO remove transient to implement LocationMetadata
    @JsonIgnore List<PatchVisitor> history = List.of();

    public String getWid() {
        return wid;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public List<LocationList> getLocationLists() {
        return locationLists;
    }

    public Map<Location, LocationMetadata> getLocationData() {
        return locationData;
    }

    public List<PatchVisitor> getHistory() {
        return history;
    }

    public void setHistory(List<PatchVisitor> history) {
        this.history = history;
    }

    public static Workspace from(String wid, List<User> collaborators, List<LocationList> locationLists){
        Workspace w = new Workspace();
        w.wid = wid;
        w.collaborators = collaborators;
        w.locationLists = locationLists;
        return w;
    }

    public static Workspace createFor(User u) {
        return from(UUID.randomUUID().toString(), new ArrayList<>(List.of(u)), new ArrayList<>(List.of(LocationList.newSaved())));
    }

}
