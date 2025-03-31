package org.tripmonkey.workspace.data.jsonpatch.applier;

import org.tripmonkey.workspace.data.Itinerary;
import org.tripmonkey.workspace.data.User;
import org.tripmonkey.workspace.data.Workspace;
import org.tripmonkey.workspace.data.jsonpatch.Patch;
import org.tripmonkey.workspace.data.jsonpatch.fields.op.OperationType;
import org.tripmonkey.workspace.data.jsonpatch.fields.path.OperationPath;
import org.tripmonkey.workspace.data.jsonpatch.fields.path.location.LocationDataPath;
import org.tripmonkey.workspace.data.location.Location;
import org.tripmonkey.workspace.data.location.data.LocationData;
import org.tripmonkey.workspace.data.location.LocationList;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Patcher implements PatchVisitor{

    private Patch p;

    private Patcher(Patch p){
        this.p = p;
    }

    private void operateOnUsers(List<User> users) throws PatchException {
        if(this.p.getContent() instanceof User u){
            switch(this.p.getOp()){
                case ADD -> {
                    if(!users.contains(u)) {
                        users.add(u);
                    }
                }
                case REMOVE -> users.removeIf(user -> user.equals(u));
                default -> throw new PatchException(
                        String.format("Invalid operation %s for field %s.",
                                p.getOp().toString(),
                                p.getTarget().toString()));
            }
        } else {
            throw new PatchException(
                    String.format("Invalid object for path %s",
                            p.getTarget().toString()));
        }
    }

    public void operateOnLocations(Workspace ws) throws PatchException {
        if(this.p.getContent() instanceof Location l){

            LocationList ll;
            try {
                int index = Integer.parseInt(this.p.getTarget().getTarget());
                try{
                    ll = ws.getLocation_lists().get(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new PatchException(String.format("Invalid target: index %d out of bounds.",index));
                }
            } catch (NumberFormatException e) {
                try {
                    UUID target = UUID.fromString(this.p.getTarget().getTarget());
                    ll = ws.getLocation_lists().stream()
                            .filter(locationList -> locationList.getId().equals(target))
                            .findFirst().orElseThrow((() -> new PatchException(
                                    String.format("Invalid target: list with id %s doesn't exist.",target))));
                } catch (IllegalArgumentException e1){
                    throw new PatchException(String.format("Invalid target: %s isn't a valid Id.",
                            this.p.getTarget().getTarget()));
                }
            }
            switch(this.p.getOp()){
                case ADD -> {
                    if(!ll.getEntries().contains(l)) {
                        ll.getEntries().add(l);
                        ws.getLocation_data().put(l, new LocationData());
                    }
                }
                case REMOVE -> ll.getEntries().removeIf(location -> location.getId().equals(l.getId()));
                default -> throw new PatchException(
                        String.format("Invalid operation %s for field %s.",
                                p.getOp().toString(),
                                p.getTarget().toString()));
            }
        } else {
            throw new PatchException(
                    String.format("Invalid object for path %s",
                            p.getTarget().toString()));
        }
    }

    @Override
    public void visitWorkspace(Workspace w) throws PatchException {
        switch(this.p.getTarget().getPath()){
            case INVITED -> {
                operateOnUsers(w.getInvited());
            }
            case COLLABORATORS -> {
                operateOnUsers(w.getCollaborators());
            }
            case LOC_LISTS -> {
                operateOnLocations(w);
            }
            case ITINERARIES -> {
                throw new PatchException("Method not implemented yet");
            }
            case LOC_DATA -> {
                String location = this.p.getTarget().getTarget();
                w.getLocation_data().entrySet().stream()
                        .filter(locationLocationDataEntry ->
                                locationLocationDataEntry.getKey().getId().equals(location))
                        .findFirst().map(Map.Entry::getValue)
                        .ifPresent(locationData -> locationData.apply(this));
            }
            default -> throw new PatchException(String.format("Invalid path: %s.", p.getTarget().toString()));
        }
        w.getHistory().add(this.p);
    }

    @Override
    public void visitItinerary(Itinerary i) throws PatchException {

    }

    @Override
    public void visitLocationData(LocationData locationData) throws PatchException {
        LocationDataPath ldp = LocationDataPath.getTargetFor(this.p.getTarget().getTarget());
        /* Implement later: use LocationDataPathTarget Enum to see the action needed - dont forget needs filtering by location id (for remove and replace) */
    }


    public static Patcher of(Patch p) throws PatchException {
        if(OperationType.INVALID.equals(p.getOp())){
            throw new PatchException(String.format("Invalid operation: %s.", p.getOp().toString()));
        }
        if(OperationPath.INVALID.equals(p.getTarget().getPath())){
            throw new PatchException(String.format("Invalid path: %s.", p.getTarget().toString()));
        }
        return new Patcher(p);
    }

}
