package org.tripmonkey.domain.data;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import org.tripmonkey.rest.domain.WorkspacePatch;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Workspace {

    @JsonbProperty String wid;
    @JsonbProperty List<User> collaborators;
    @JsonbProperty List<LocationList> locationLists;
    @JsonbTransient Map<Location, LocationMetadata> locationData; //TODO remove transient to implement LocationMetadata
    @JsonbTransient List<WorkspacePatch> history = List.of();

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

    public List<WorkspacePatch> getHistory() {
        return history;
    }

    public void setHistory(List<WorkspacePatch> history) {
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
        return from(UUID.randomUUID().toString(), List.of(u), List.of(LocationList.newSaved()));
    }

}
