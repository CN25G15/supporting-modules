package org.tripmonkey.domain.patch;

import org.tripmonkey.domain.data.Location;
import org.tripmonkey.domain.data.LocationList;
import org.tripmonkey.domain.data.User;
import org.tripmonkey.domain.data.Workspace;
import org.tripmonkey.rest.domain.value.CommentPatch;
import org.tripmonkey.rest.domain.value.LocationListPatch;
import org.tripmonkey.rest.domain.value.LocationMetadataPatch;
import org.tripmonkey.rest.domain.value.LocationPatch;
import org.tripmonkey.rest.domain.value.UserPatch;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;
import org.tripmonkey.rest.patch.fields.path.TargetType;


public class WorkspacePatcher implements PatchApplier {

    Workspace w;

    public static WorkspacePatcher forWorkspace(Workspace w) {
        WorkspacePatcher wpr = new WorkspacePatcher();
        wpr.w = w;
        return wpr;
    }

    @Override
    public void apply(UserPatch up) throws WorkspacePatchException {
        switch(up.getOp()){
            case ADD ->User.from(up.getValue())
                    .stream().findFirst()
                    .filter(user -> !w.getCollaborators().contains(user))
                    .map(user -> w.getCollaborators().add(user))
                    .orElseThrow(() ->  new WorkspacePatchException("Invalid UUID for user."));
            case REMOVE -> {
                User u = User.from(up.getPath().getTarget())
                    .stream().findFirst()
                        .orElseThrow(() -> new WorkspacePatchException(String.format("Invalid UUID for user: %s",
                                up.getValue().toString())));
                w.getCollaborators().stream()
                        .filter(user -> user.equals(u))
                        .findFirst()
                        .map(user -> w.getCollaborators().remove(user))
                        .orElseThrow(() -> new WorkspacePatchException("User doesn't exist in workspace: %s"));
            }
            default -> throw new WorkspacePatchException(String.format("Invalid operation %s for field %s.",
                    up.getOp().toString(),
                    up.getPath().toString()));
        }
    }

    @Override
    public void apply(LocationPatch lp) throws WorkspacePatchException {

        switch(lp.getPath().getTargetType()){

            case LOC_LISTS -> {
                if(lp.getOp().equals(Op.ADD)){
                    String list_index = lp.getPath().getTarget();
                    int index = 0;

                    try{
                        index = Integer.parseInt(list_index);
                    } catch (NumberFormatException e) {
                        throw new WorkspacePatchException(String.format("Invalid list Id format: %s.", list_index));
                    }

                    LocationList ll;
                    try {
                        ll = w.getLocationLists().get(index);
                    } catch (RuntimeException e) {
                        throw new WorkspacePatchException(String.format("Invalid list Id: index %d doesn't exist.",index));
                    }

                    Location l = Location.from(lp.getValue());

                    if(!ll.getLocations().contains(l))
                        ll.getLocations().add(l);
                    return;
                }
                throw new WorkspacePatchException(String.format("Invalid operation %s for field %s.",
                        lp.getOp().toString(),
                        lp.getPath().toString()));


            }
            case ITINERARIES ->  throw new WorkspacePatchException("Not Implemented");
            default -> throw new WorkspacePatchException(String.format("Invalid operation %s for field %s.",
                    lp.getOp().toString(),
                    lp.getPath().toString()));
        }

    }

    @Override
    public void apply(LocationListPatch llp) throws WorkspacePatchException {

        switch(llp.getOp()) {
            case ADD -> {

                /* Create new List */
                if(!llp.getPath().hasNextNode()) {
                    w.getLocationLists().add(LocationList.from(llp.getValue()));
                    return;
                }
                if(!llp.getPath().hasNextNode())
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",llp.getPath().toString()));

                throw new WorkspacePatchException(String.format("Invalid request for operation %s on field %s",
                        llp.getOp().toString(),
                        llp.getPath().toString()));
            }
/*            case REMOVE -> {

                String list_index = llp.getPath().getTarget();
                int index = 0;

                try{
                    index = Integer.parseInt(list_index);
                } catch (NumberFormatException e) {
                    throw new WorkspacePatchException(String.format("Invalid list Id format: %s.", list_index));
                }

                if(!llp.getPath().hasNextNode()) {
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

                PathNode next = llp.getPath().getNextNode();

                if(next.hasNextNode() || next.getTarget() == null){
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",llp.getPath().toString()));
                }

                ll.getLocations().removeIf(loc -> loc.getPlaceId().equals(next.getTarget()));
            }
            case REPLACE -> {

                String list_index = llp.getPath().getTarget();
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

                if(!llp.getPath().hasNextNode())
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",llp.getPath().toString()));

                PathNode next = llp.getPath().getNextNode();

                if(next.hasNextNode() || next.getTarget() != null || !next.getTargetType().equals(TargetType.NAME)){
                    throw new WorkspacePatchException(
                            String.format("Invalid request for field %s.",llp.getPath().toString()));
                }

            }*/
            default -> throw new WorkspacePatchException(String.format("Invalid operation %s for field %s.",
                    llp.getOp().toString(),
                    llp.getPath().toString()));
        }

    }

    @Override
    public void apply(CommentPatch cp) throws WorkspacePatchException {
        throw new WorkspacePatchException(String.format("Unimplemented operation %s for field %s.",
                cp.getOp().toString(),
                cp.getPath().toString()));
    }

    @Override
    public void apply(LocationMetadataPatch lmp) throws WorkspacePatchException {
        throw new WorkspacePatchException(String.format("Unimplemented operation %s for field %s.",
                lmp.getOp().toString(),
                lmp.getPath().toString()));
    }

    public void apply(PatchVisitor pv) throws WorkspacePatchException {
        switch(pv){
            case CommentPatch cp -> cp.visit(this);
            case LocationListPatch llp -> llp.visit(this);
            case LocationPatch lp -> lp.visit(this);
            case UserPatch up -> up.visit(this);
            case LocationMetadataPatch lmp -> lmp.visit(this);
            default -> throw new IllegalStateException("Unexpected value: " + pv);
        }
    }

}
