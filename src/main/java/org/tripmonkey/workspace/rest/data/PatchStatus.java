package org.tripmonkey.workspace.rest.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.patcher.WorkspacePatch;

public class PatchStatus {

    @JsonbProperty WorkspacePatch patch;
    @JsonbProperty int status;

    public static PatchStatus of(WorkspacePatch wp, int status) {
        PatchStatus ps = new PatchStatus();
        ps.patch = wp;
        ps.status = status;
        return ps;
    }

    public WorkspacePatch getPatch() {
        return patch;
    }

    public int getStatus() {
        return status;
    }
}
