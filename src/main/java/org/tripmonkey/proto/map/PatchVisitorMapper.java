package org.tripmonkey.proto.map;

import org.tripmonkey.domain.patch.PatchVisitor;
import org.tripmonkey.rest.domain.value.CommentPatch;
import org.tripmonkey.rest.domain.value.LocationListPatch;
import org.tripmonkey.rest.domain.value.LocationMetadataPatch;
import org.tripmonkey.rest.domain.value.LocationPatch;
import org.tripmonkey.rest.domain.value.UserPatch;

public class PatchVisitorMapper extends ProtoMapper<PatchVisitor, org.tripmonkey.patch.data.WorkspacePatch> {

    protected static CommentPatchMapper commentPatchMapper = new CommentPatchMapper();
    protected static LocationListPatchMapper locationListPatchMapper = new LocationListPatchMapper();
    protected static LocationMetadataPatchMapper locationMetadataPatchMapper = null;
    protected static LocationPatchMapper locationPatchMapper = new LocationPatchMapper();
    protected static UserPatchMapper userPatchMapper = new UserPatchMapper();

    @Override
    public org.tripmonkey.patch.data.WorkspacePatch serialize(PatchVisitor d) {
        return switch(d){
            case CommentPatch cp -> commentPatchMapper.serialize(cp);
            case LocationListPatch llp -> locationListPatchMapper.serialize(llp);
            case LocationMetadataPatch lmp -> null;
            case LocationPatch lp -> locationPatchMapper.serialize(lp);
            case UserPatch up -> userPatchMapper.serialize(up);
            default -> null;
        };
    }

    @Override
    public PatchVisitor deserialize(org.tripmonkey.patch.data.WorkspacePatch g) {
        PatchVisitor pv = null;
        if(g.hasComment())
            pv = commentPatchMapper.deserialize(g);
        if(g.hasLocation())
            pv = locationPatchMapper.deserialize(g);
        if(g.hasLocationlist())
            pv = locationListPatchMapper.deserialize(g);
        if(g.hasLocationmetadata())
            pv = null;
        if(g.hasUserId())
            pv = userPatchMapper.deserialize(g);
        return pv;
    }
}
