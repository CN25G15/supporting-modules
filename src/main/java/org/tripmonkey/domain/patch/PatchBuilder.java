package org.tripmonkey.domain.patch;

import org.tripmonkey.domain.data.User;
import org.tripmonkey.rest.domain.value.CommentPatch;
import org.tripmonkey.rest.domain.value.LocationListPatch;
import org.tripmonkey.rest.domain.value.LocationMetadataPatch;
import org.tripmonkey.rest.domain.value.LocationPatch;
import org.tripmonkey.rest.domain.value.UserPatch;
import org.tripmonkey.rest.patch.Patch;
import org.tripmonkey.rest.patch.fields.Op;

public class PatchBuilder {

    public static PatchVisitor from(String wid, User u, Patch p) {

        PatchVisitor pv = null;

        switch(p.getPath().getTargetType()){
            case COLLABORATORS -> {
                if(Op.ADD.equals(p.getOp()) && p.getValue().isUser()){
                    pv = p.getValue().asUser()
                            .map(userDTO -> new UserPatch(wid, u.toString(), p.getOp(), p.getPath(), userDTO))
                            .orElse(null);
                }
                if(Op.REMOVE.equals(p.getOp()) && p.getValue() == null){
                    pv = new UserPatch(wid, u.toString(), p.getOp(), p.getPath(), null);
                }
            }
            case LOC_LISTS -> {
                if(!p.getPath().hasNextNode() && Op.ADD.equals(p.getOp()) && p.getValue().isLocation()) {
                    pv = new LocationPatch(wid, u.toString(), p.getOp(), p.getPath(), p.getValue().asLocation());
                }
                if(!p.getPath().hasNextNode() && Op.ADD.equals(p.getOp()) && p.getValue().isLocationList()) {
                    pv = new LocationListPatch(wid, u.toString(), p.getOp(), p.getPath(), p.getValue().asLocationList());
                }
            }
            case ITINERARIES -> {
                pv = null;
            }
            case LOC_DATA -> {
                //pv = new LocationMetadataPatch(wid, u.toString(), p.getOp(), p.getPath(), p.getValue().asLocationMetadata());
            }
            // pv = new CommentPatch(wid, u.toString(), p.getOp(), p.getPath(), p.getValue().asComment());
        }


        return pv;
    }

}
