package org.tripmonkey.workspace.patcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.workspace.domain.User;
import org.tripmonkey.workspace.rest.data.Patch;

import java.util.UUID;

public class WorkspacePatch {

    @JsonProperty UUID workspace;
    @JsonProperty User user;
    @JsonProperty Patch patch;

    public static WorkspacePatch of(UUID workspace, User u, Patch p) {
        WorkspacePatch wp = new WorkspacePatch();
        wp.workspace = workspace;
        wp.user = u;
        wp.patch = p;
        return wp;
    }

    public static WorkspacePatch of(String workspaceid, User u, Patch p){
        try {
            UUID workspace = UUID.fromString(workspaceid);
            return of(workspace,u,p);
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Invalid workspace UUID received.");
        }
    }

    public UUID getWorkspace() {
        return workspace;
    }

    public User getUser() {
        return user;
    }

    public Patch getPatch() {
        return patch;
    }
}
