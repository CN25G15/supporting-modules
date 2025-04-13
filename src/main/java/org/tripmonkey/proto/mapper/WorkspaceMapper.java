package org.tripmonkey.proto.mapper;

import org.tripmonkey.domain.data.Workspace;

public class WorkspaceMapper extends ProtoSerde<Workspace, org.tripmonkey.workspace.service.Workspace> {
    @Override
    protected org.tripmonkey.workspace.service.Workspace serialize(Workspace d) {
        return org.tripmonkey.workspace.service.Workspace.newBuilder()
                .setWid(d.getWid())
                .addAllCollaborators(d.getCollaborators().stream().map(userMapper::serialize).toList())
                .addAllLocationLists(d.getLocationLists().stream().map(locationListMapper::serialize).toList())
                .build();
    }

    @Override
    protected Workspace deserialize(org.tripmonkey.workspace.service.Workspace g) {
        return Workspace.from(
                g.getWid(),
                g.getCollaboratorsList().stream().map(userMapper::deserialize).toList(),
                g.getLocationListsList().stream().map(locationListMapper::deserialize).toList()
        );
    }
}
