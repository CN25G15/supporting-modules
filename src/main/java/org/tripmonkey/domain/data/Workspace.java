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
    @JsonbTransient List<WorkspacePatch> history;

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

    public static Workspace from(String wid, List<User> collaborators, List<LocationList> locationLists){
        Workspace w = new Workspace();
        w.wid = wid;
        w.collaborators = collaborators;
        w.locationLists = locationLists;
        return w;
    }

}
