package org.tripmonkey.workspace.data.jsonpatch.fields.path;

import java.util.Arrays;

public enum OperationPath {
    INVITED("/workspace/invited"),
    COLLABORATORS("/workspace/collaborators"),
    LOC_LISTS("/workspace/location_lists"),
    ITINERARIES("/workspace/itineraries"),
    LOC_DATA("/workspace/location_data"),
    INVALID(null);

    private String path;

    OperationPath(String s) {
        this.path = s;
    }

    public static OperationPath getPathFor(String s) {
        return Arrays.stream(values()).filter(path -> s.contains(path.path)).findFirst().orElse(INVALID);
    }

    public String getPath() {
        return path;
    }

}
