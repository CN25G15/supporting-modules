package org.tripmonkey.workspace.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.domain.location.LocationDataWrapper;
import org.tripmonkey.workspace.patcher.WorkspacePatch;
import org.tripmonkey.workspace.rest.data.Patch;
import org.tripmonkey.workspace.patcher.PatchVisitor;
import org.tripmonkey.workspace.domain.location.Location;
import org.tripmonkey.workspace.domain.location.LocationData;
import org.tripmonkey.workspace.domain.location.LocationList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Workspace {

    @JsonbProperty private UUID wid;
    @JsonbProperty private User owner;
    @JsonbProperty private List<User> invited = new ArrayList<>();
    @JsonbProperty private List<User> collaborators =  new ArrayList<>();
    @JsonbProperty private List<LocationList> location_lists = new ArrayList<>();
    @JsonbProperty private List<Itinerary> itineraries = new ArrayList<>();
    @JsonbProperty private Map<Location, LocationData> location_data = new HashMap<>();
    @JsonbProperty private List<WorkspacePatch> history = new ArrayList<>();

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

    public List<WorkspacePatch> getHistory() {
        return history;
    }

    public Map<Location, LocationData> getLocation_data() {
        return location_data;
    }

    public boolean addLocationData(LocationDataWrapper ldw){
        if(!this.location_data.containsKey(ldw.getLocation())){
            this.location_data.put(ldw.getLocation(), ldw.getLocationData());
            return true;
        }
        return false;
    }
}
