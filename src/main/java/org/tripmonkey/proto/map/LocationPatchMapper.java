package org.tripmonkey.proto.map;

import org.tripmonkey.patch.data.WorkspacePatch;
import org.tripmonkey.rest.domain.value.LocationPatch;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class LocationPatchMapper extends ProtoMapper<LocationPatch, WorkspacePatch> {
    @Override
    protected WorkspacePatch serialize(LocationPatch d) {
        return WorkspacePatch.newBuilder().setWorkspaceId(d.getWorkspaceId()).setUser(d.getUser()).setOp(patchOperationMapper.serialize(d.getOp())).setPath(d.getPath().toString()).setLocation(locationSerde.serialize(d.getValue())).build();
    }

    @Override
    protected LocationPatch deserialize(WorkspacePatch g) {
        return new LocationPatch(g.getWorkspaceId(), g.getUser(), patchOperationMapper.deserialize(g.getOp()), PathNode.from(g.getPath()), locationSerde.deserialize(g.getLocation()));
    }

}
