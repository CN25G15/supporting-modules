package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.rest.data.PatchStatus;

public class PatchStatusSerde extends
        GRPCSerde<PatchStatus, org.tripmonkey.patch.data.PatchStatus>{

    protected PatchStatusSerde(){}

    @Override
    protected org.tripmonkey.patch.data.PatchStatus serialize(PatchStatus d) {
        return org.tripmonkey.patch.data.PatchStatus.newBuilder()
                .setStatus(d.getStatus())
                .setUserPatch(workspacePatchSerde.serialize(d.getPatch()))
                .build();
    }

    @Override
    protected PatchStatus deserialize(org.tripmonkey.patch.data.PatchStatus g) {
        return PatchStatus.of(workspacePatchSerde.deserialize(g.getUserPatch()),(int) g.getStatus());
    }
}
