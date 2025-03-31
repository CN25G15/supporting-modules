package org.tripmonkey.workspace.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.workspace.data.jsonpatch.Patch;
import org.tripmonkey.workspace.data.jsonpatch.applier.PatchVisitor;
import org.tripmonkey.workspace.data.location.Location;
import org.tripmonkey.workspace.data.location.data.LocationData;
import org.tripmonkey.workspace.data.location.LocationList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Workspace {

    @JsonProperty private UUID wid;
    @JsonProperty private User owner;
    @JsonProperty private List<User> invited = new ArrayList<>();
    @JsonProperty private List<User> collaborators =  new ArrayList<>();
    @JsonProperty private List<LocationList> location_lists = new ArrayList<>();
    @JsonProperty private List<Itinerary> itineraries = new ArrayList<>();
    @JsonProperty private Map<Location, LocationData> location_data = new HashMap<>();
    @JsonProperty private List<Patch> history = new ArrayList<>();

    public Workspace(User owner){
        this.wid = UUID.randomUUID();
        this.owner = owner;
        this.location_lists.add(LocationList.emptySaved(this.owner));
    }

    public void apply(PatchVisitor pv){
        pv.visitWorkspace(this);
    }

    public UUID getWid() {
        return wid;
    }

    public User getOwner() {
        return owner;
    }

    public List<User> getInvited() {
        return invited;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public List<LocationList> getLocation_lists() {
        return location_lists;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public List<Patch> getHistory() {
        return history;
    }

    public Map<Location, LocationData> getLocation_data() {
        return location_data;
    }
}
