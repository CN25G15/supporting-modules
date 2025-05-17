package org.tripmonkey.proto.map;

import org.tripmonkey.patch.data.WorkspacePatch;
import org.tripmonkey.rest.domain.value.LocationListPatch;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class LocationListPatchMapper extends ProtoMapper<LocationListPatch, WorkspacePatch> {
    @Override
    protected WorkspacePatch serialize(LocationListPatch d) {
        return WorkspacePatch.newBuilder().setWorkspaceId(d.getWorkspaceId()).setUser(d.getUser()).setOp(patchOperationMapper.serialize(d.getOp())).setPath(d.getPath().toString()).setLocationlist(locationListDTOMapper.serialize(d.getValue())).build();
    }

    @Override
    protected LocationListPatch deserialize(WorkspacePatch g) {
        return new LocationListPatch(g.getWorkspaceId(), g.getUser(), patchOperationMapper.deserialize(g.getOp()), PathNode.from(g.getPath()), locationListDTOMapper.deserialize(g.getLocationlist()));
    }

}
