package org.tripmonkey.workspace.data.jsonpatch.fields.path.location;

import java.util.Arrays;

public enum LocationDataPathTarget {
    DESCRIPTION("/description"),
    COMMENTS("/comments"),
    TAGS("/tags"),
    INVALID(null);

    private String path;

    LocationDataPathTarget(String s){
        this.path = s;
    }

    public static LocationDataPathTarget getPathFor(String s) {
        return Arrays.stream(values()).filter(path -> s.contains(path.path)).findFirst().orElse(INVALID);
    }

    public String getPath() {
        return path;
    }

}
