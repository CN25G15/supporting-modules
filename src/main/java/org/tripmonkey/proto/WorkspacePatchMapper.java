package org.tripmonkey.proto;

import org.tripmonkey.rest.domain.WorkspacePatch;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class WorkspacePatchMapper extends ProtoSerde<WorkspacePatch, org.tripmonkey.patch.data.WorkspacePatch> {

    @Override
    public org.tripmonkey.patch.data.WorkspacePatch serialize(WorkspacePatch d) {
        return org.tripmonkey.patch.data.WorkspacePatch.newBuilder()
                .setWorkspaceId(d.getWorkspaceId())
                .setUser(d.getUser())
                .setOp(patchOperationMapper.serialize(d.getOp()))
                .setPath(d.getPath().toString())
                .setValue(valueWrapperMapper.serialize(d.getValue())).build();
    }

    @Override
    public WorkspacePatch deserialize(org.tripmonkey.patch.data.WorkspacePatch g) {
        return WorkspacePatch.fromArgs(g.getWorkspaceId(),
                g.getUser(),
                patchOperationMapper.deserialize(g.getOp()),
                PathNode.from(g.getPath()),
                valueWrapperMapper.deserialize(g.getValue())
                );
    }
}
