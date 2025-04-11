package org.tripmonkey.workspace.rest.data.fields.path;

import java.util.Arrays;

public enum OperationPath {
    INVITED("/invited"),
    COLLABORATORS("/collaborators"),
    LOC_LISTS("/location_lists"),
    ITINERARIES("/itineraries"),
    LOC_DATA("/location_data"),
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
