package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.patcher.WorkspacePatch;

public class WorkspacePatchSerde extends GRPCSerde<WorkspacePatch, org.tripmonkey.patch.data.WorkspacePatch> {

    protected WorkspacePatchSerde(){}

    @Override
    protected org.tripmonkey.patch.data.WorkspacePatch serialize(WorkspacePatch d) {
        return org.tripmonkey.patch.data.WorkspacePatch.newBuilder()
                .setPatch(jsonPatchSerde.serialize(d.getPatch()))
                .setWorkspaceId(d.getWorkspace().toString())
                .setUser(userSerde.serialize(d.getUser()))
                .build();
    }

    @Override
    protected WorkspacePatch deserialize(org.tripmonkey.patch.data.WorkspacePatch g) {
        return WorkspacePatch.of(g.getWorkspaceId(),
                userSerde.deserialize(g.getUser()),
                jsonPatchSerde.deserialize(g.getPatch()));
    }
}
