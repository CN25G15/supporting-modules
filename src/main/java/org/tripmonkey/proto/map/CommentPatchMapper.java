package org.tripmonkey.proto.map;

import org.tripmonkey.patch.data.WorkspacePatch;
import org.tripmonkey.rest.domain.value.CommentPatch;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class CommentPatchMapper extends ProtoMapper<CommentPatch, WorkspacePatch> {
    @Override
    protected WorkspacePatch serialize(CommentPatch d) {
        return WorkspacePatch.newBuilder().setWorkspaceId(d.getWorkspaceId()).setUser(d.getUser()).setOp(patchOperationMapper.serialize(d.getOp())).setPath(d.getPath().toString()).setComment(commentSerde.serialize(d.getValue())).build();
    }

    @Override
    protected CommentPatch deserialize(WorkspacePatch g) {
        return new CommentPatch(g.getWorkspaceId(), g.getUser(), patchOperationMapper.deserialize(g.getOp()), PathNode.from(g.getPath()), commentSerde.deserialize(g.getComment()));
    }

}
