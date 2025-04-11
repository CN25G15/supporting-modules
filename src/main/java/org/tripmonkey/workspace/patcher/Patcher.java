package org.tripmonkey.workspace.patcher;

import org.tripmonkey.workspace.domain.Itinerary;
import org.tripmonkey.workspace.domain.User;
import org.tripmonkey.workspace.domain.Workspace;
import org.tripmonkey.workspace.domain.location.LocationDataWrapper;
import org.tripmonkey.workspace.rest.data.fields.op.OperationType;
import org.tripmonkey.workspace.rest.data.fields.path.OperationPath;
import org.tripmonkey.workspace.domain.location.Location;
import org.tripmonkey.workspace.domain.location.LocationData;
import org.tripmonkey.workspace.domain.location.LocationList;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Patcher implements PatchVisitor{

    private WorkspacePatch p;

    private Patcher(WorkspacePatch p){
        this.p = p;
    }

    private void operateOnUsers(List<User> users) throws PatchException {
        Object content = this.p.getPatch().getValue();
        if(content instanceof User u){
            switch(this.p.getPatch().getOp()){
                case ADD -> {
                    if(!users.contains(u)) {
                        users.add(u);
                    }
                    break;
                }
                case REMOVE -> {
                    users.removeIf(user -> user.equals(u));
                    break;
                }
                default -> throw new PatchException(
                        String.format("Invalid operation %s for field %s.",
                                p.getPatch().getOp().toString(),
                                p.getPatch().getTarget().toString()));
            }
        } else {
            throw new PatchException(
                    String.format("Invalid object for path %s",
                            p.getPatch().getTarget().toString()));
        }
    }

    public void operateOnLocations(Workspace ws) throws PatchException {
        if(this.p.getPatch().getValue() instanceof Location l){

            LocationList ll;
            try {
                int index = Integer.parseInt(this.p.getPatch().getTarget().getTarget(3));
                try{
                    ll = ws.getLocation_lists().get(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new PatchException(String.format("Invalid target: index %d out of bounds.",index));
                }
            } catch (NumberFormatException e) {
                try {
                    UUID target = UUID.fromString(this.p.getPatch().getTarget().getTarget(3));
                    ll = ws.getLocation_lists().stream()
                            .filter(locationList -> locationList.getId().equals(target))
                            .findFirst().orElseThrow((() -> new PatchException(
                                    String.format("Invalid target: list with id %s doesn't exist.",target))));
                } catch (IllegalArgumentException e1){
                    throw new PatchException(String.format("Invalid target: %s isn't a valid Id.",
                            this.p.getPatch().getTarget().getTarget(3)));
                }
            }
            switch(this.p.getPatch().getOp()){
                case ADD -> {
                    if(!ll.getEntries().contains(l)) {
                         ll.getEntries().add(l);
                        ws.getLocation_data().put(l, new LocationData());
                    }
                    break;
                }
                case REMOVE -> {
                    ll.getEntries().removeIf(location -> location.getId().equals(l.getId()));
                    break;
                }
                default -> throw new PatchException(
                        String.format("Invalid operation %s for field %s.",
                                p.getPatch().getOp().toString(),
                                p.getPatch().getTarget().toString()));
            }
        } else {
            throw new PatchException(
                    String.format("Invalid object for path %s",
                            p.getPatch().getTarget().toString()));
        }
    }

    @Override
    public void visitWorkspace(Workspace w) throws PatchException {
        switch(this.p.getPatch().getTarget().getPath()){
            case INVITED -> {
                operateOnUsers(w.getInvited());
                break;
            }
            case COLLABORATORS -> {
                operateOnUsers(w.getCollaborators());
                break;
            }
            case LOC_LISTS -> {
                operateOnLocations(w);
                break;
            }
            case ITINERARIES -> {
                throw new PatchException("Method not implemented yet");
            }
            case LOC_DATA -> {
                Object value = this.p.getPatch().getValue();
                switch(this.p.getPatch().getOp()){
                    case ADD -> {
                        if(value instanceof LocationDataWrapper ldw){
                            if(!w.addLocationData(ldw)){
                                throw new PatchException(
                                        String.format("Key error: %s already exists",ldw.getLocation()));
                            }
                        } else {
                            throw new PatchException(
                                    String.format("Invalid object for operation %s at field %s",
                                            p.getPatch().getOp().toString(),
                                            p.getPatch().getTarget().toString()));
                        }
                        break;
                    }
                    case REMOVE -> {
                        Location loc = Location.from(this.p.getPatch().getTarget().getTarget(3));
                        w.getLocation_data()
                                .entrySet().removeIf(entry -> entry.getKey().equals(loc));
                        break;
                    }
                    case REPLACE -> {
                        Location loc = Location.from(this.p.getPatch().getTarget().getTarget(3));
                        if(value instanceof LocationData ld) {
                            if(!w.getLocation_data().containsKey(loc)){
                                w.getLocation_data().put(loc,new LocationData());
                            }
                            w.getLocation_data().get(loc).merge(ld);
                        } else {
                            throw new PatchException(
                                    String.format("Invalid object for operation %s at field %s",
                                            p.getPatch().getOp().toString(),
                                            p.getPatch().getTarget().toString()));
                        }
                        break;
                    }
                }
            }
            default -> throw new PatchException(String.format("Invalid path: %s.",
                    p.getPatch().getTarget().getTarget(0).toString()));
        }
        w.getHistory().add(this.p);
    }

    @Override
    public void visitItinerary(Itinerary i) throws PatchException {

    }

    @Override
    public void visitLocationData(LocationData locationData) throws PatchException {
/*
        Object value = this.p.getPatch().getValue();
        switch(){
            case ADD -> {
                if(value instanceof LocationData ld){

                } else {

                }
                break;
            }
            case REMOVE -> {
                break;
            }
            case REPLACE -> {
                break;
            }
            default -> throw new PatchException(String.format("Invalid path: %s.", p.getPatch().getTarget().toString()));
        }*/
    }


    public static Patcher of(WorkspacePatch p) throws PatchException {
        if(OperationType.INVALID.equals(p.getPatch().getOp())){
            throw new PatchException(
                    String.format("Invalid operation: %s.", p.getPatch().getOp().toString()));
        }
        if(OperationPath.INVALID.equals(p.getPatch().getTarget().getPath())){
            throw new PatchException(
                    String.format("Invalid path: %s.", p.getPatch().getTarget().toString()));
        }
        return new Patcher(p);
    }

}
