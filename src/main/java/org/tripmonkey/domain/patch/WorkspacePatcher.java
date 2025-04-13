package org.tripmonkey.domain.patch;


import org.tripmonkey.domain.data.Location;
import org.tripmonkey.domain.data.LocationList;
import org.tripmonkey.domain.data.User;
import org.tripmonkey.domain.data.Workspace;
import org.tripmonkey.rest.domain.WorkspacePatch;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.domain.value.ValueType;
import org.tripmonkey.rest.domain.value.ValueWrapper;
import org.tripmonkey.rest.patch.fields.path.PathNode;
import org.tripmonkey.rest.patch.fields.path.TargetType;

import java.util.List;
import java.util.Optional;

public class WorkspacePatcher {

    WorkspacePatch wp;

    public static WorkspacePatcher forPatch(WorkspacePatch wp) {
        WorkspacePatcher wpr = new WorkspacePatcher();
        wpr.wp = wp;
        return wpr;
    }

    public void setWorkspacePatch(WorkspacePatch wp){
        this.wp = wp;
    }

    private void operateOnUsers(List<User> users) throws WorkspacePatchException  {
        ValueWrapper vw = this.wp.getValue();
        switch(this.wp.getOp()){
            case ADD -> {
                if(!ValueType.USER.equals(vw.getType()) && vw.getValue() instanceof UserDTO u){
                    User.from(u).stream().findFirst().filter(user -> !users.contains(user)).map(users::add)
                            .orElseThrow(() ->  new WorkspacePatchException("Invalid UUID for user."));
                }
            }
            case REMOVE -> {
                String userId = this.wp.getPath().getTarget();
                Optional.ofNullable(userId).map(User::from)
                        .orElseThrow(() ->  new WorkspacePatchException("Invalid path for operation remove. No user received."))
                        .map(toRemove -> users.removeIf(user -> toRemove.toString().equals(user.toString())))
                        .orElseThrow(() ->  new WorkspacePatchException("Invalid UUID for user."));
            }
            default -> throw new WorkspacePatchException(String.format("Invalid operation %s for field %s.",
                    this.wp.getOp().toString(),
                    this.wp.getPath().toString()));
        }
    }

    public void operateOnLocLists(List<LocationList> lll) {

        switch(this.wp.getOp()) {
            case ADD -> {

                /* Create new List */
                if(!this.wp.getPath().hasNextNode()
                        && this.wp.getValue().getType().equals(ValueType.LOC_LIST)
                        && this.wp.getValue().getValue() instanceof LocationListDTO listDTO) {
                    lll.add(LocationList.from(listDTO));
                    return;
                }
                if(!this.wp.getPath().hasNextNode())
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",this.wp.getPath().toString()));

                PathNode next = this.wp.getPath().getNextNode();

                if(next.hasNextNode() || next.getTarget() != null){
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",this.wp.getPath().toString()));
                }

                /* Add location to existing list */
                if(next.getTargetType().equals(TargetType.LOCATIONS) &&
                this.wp.getValue().getType().equals(ValueType.LOCATION) &&
                this.wp.getValue().getValue() instanceof LocationDTO ldto) {

                    String list_index = this.wp.getPath().getTarget();
                    int index = 0;

                    try{
                        index = Integer.parseInt(list_index);
                    } catch (NumberFormatException e) {
                        throw new WorkspacePatchException(String.format("Invalid list Id format: %s.", list_index));
                    }

                    LocationList ll;
                    try {
                        ll = lll.get(index);
                    } catch (RuntimeException e) {
                        throw new WorkspacePatchException(String.format("Invalid list Id: index %d doesn't exist.",index));
                    }

                    Location l = Location.from(ldto);

                    if(!ll.getLocations().contains(l))
                        ll.getLocations().add(l);
                    return;
                }

                throw new WorkspacePatchException(String.format("Invalid request for operation %s on field %s",
                        this.wp.getOp().toString(),
                        this.wp.getPath().toString()));
            }
            case REMOVE -> {

                String list_index = this.wp.getPath().getTarget();
                int index = 0;

                try{
                    index = Integer.parseInt(list_index);
                } catch (NumberFormatException e) {
                    throw new WorkspacePatchException(String.format("Invalid list Id format: %s.", list_index));
                }

                if(!this.wp.getPath().hasNextNode()) {
                    try {
                        lll.remove(index);
                        return;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new WorkspacePatchException(String.format("Invalid list Id: index %d doesn't exist.",index));
                    }
                }

                LocationList ll;
                try {
                    ll = lll.get(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new WorkspacePatchException(String.format("Invalid list Id: index %d doesn't exist.",index));
                }

                PathNode next = this.wp.getPath().getNextNode();

                if(next.hasNextNode() || next.getTarget() == null){
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",this.wp.getPath().toString()));
                }

                ll.getLocations().removeIf(loc -> loc.getPlaceId().equals(next.getTarget()));
            }
            case REPLACE -> {

                String list_index = this.wp.getPath().getTarget();
                int index = 0;

                try{
                    index = Integer.parseInt(list_index);
                } catch (NumberFormatException e) {
                    throw new WorkspacePatchException(String.format("Invalid list Id format: %s.", list_index));
                }

                LocationList ll;
                try {
                    ll = lll.get(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new WorkspacePatchException(String.format("Invalid list Id: index %d doesn't exist.",index));
                }

                if(!this.wp.getPath().hasNextNode())
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",this.wp.getPath().toString()));

                PathNode next = this.wp.getPath().getNextNode();

                if(next.hasNextNode() || next.getTarget() != null || !next.getTargetType().equals(TargetType.NAME)){
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",this.wp.getPath().toString()));
                }

            }
            default -> throw new WorkspacePatchException(String.format("Invalid operation %s for field %s.",
                    this.wp.getOp().toString(),
                    this.wp.getPath().toString()));
        }

    }


    public void apply(Workspace ws){

        switch(wp.getPath().getTargetType()){
            case COLLABORATORS -> operateOnUsers(ws.getCollaborators());
            case LOC_LISTS -> operateOnLocLists(ws.getLocationLists());
            case LOC_DATA -> throw new WorkspacePatchException("Unimplemented field location_data");
            case ITINERARIES -> throw new WorkspacePatchException("Unimplemented field itineraries");
            default -> throw new WorkspacePatchException("Invalid operation");
        }


    }

}
