package org.tripmonkey.proto.map;

import org.tripmonkey.patch.data.WorkspacePatch;
import org.tripmonkey.rest.domain.value.UserPatch;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class UserPatchMapper extends ProtoMapper<UserPatch, WorkspacePatch> {
    @Override
    protected WorkspacePatch serialize(UserPatch d) {
        return WorkspacePatch.newBuilder().setWorkspaceId(d.getWorkspaceId()).setUser(d.getUser()).setOp(patchOperationMapper.serialize(d.getOp())).setPath(d.getPath().toString()).setUserId(userSerde.serialize(d.getValue())).build();
    }

    @Override
    protected UserPatch deserialize(WorkspacePatch g) {
        return new UserPatch(g.getWorkspaceId(), g.getUser(), patchOperationMapper.deserialize(g.getOp()), PathNode.from(g.getPath()), userSerde.deserialize(g.getUserId()));
    }

}
