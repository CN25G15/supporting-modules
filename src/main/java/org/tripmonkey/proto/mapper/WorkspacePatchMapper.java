package org.tripmonkey.proto.mapper;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import org.tripmonkey.rest.domain.WorkspacePatch;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.domain.value.ValueWrapper;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class WorkspacePatchMapper extends ProtoSerde<WorkspacePatch, org.tripmonkey.patch.data.WorkspacePatch> {

    @Override
    protected org.tripmonkey.patch.data.WorkspacePatch serialize(WorkspacePatch d) {
        return org.tripmonkey.patch.data.WorkspacePatch.newBuilder()
                .setWorkspaceId(d.getWorkspaceId())
                .setUser(d.getUser())
                .setOp(patchOperationMapper.serialize(d.getOp()))
                .setPath(d.getPath().toString())
                .setValue(valueWrapperMapper.serialize(d.getValue())).build();
    }

    @Override
    protected WorkspacePatch deserialize(org.tripmonkey.patch.data.WorkspacePatch g) {
        return WorkspacePatch.fromArgs(g.getWorkspaceId(),
                g.getUser(),
                patchOperationMapper.deserialize(g.getOp()),
                PathNode.from(g.getPath()),
                valueWrapperMapper.deserialize(g.getValue())
                );
    }
}
