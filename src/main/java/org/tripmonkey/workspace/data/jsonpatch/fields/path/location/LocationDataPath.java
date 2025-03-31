package org.tripmonkey.workspace.data.jsonpatch.fields.path.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LocationDataPath {

    private String pathString;
    private LocationDataPathTarget lpt;
    private String location_id;

    @JsonCreator
    public static LocationDataPath getTargetFor(String s) {
        LocationDataPath lp = new LocationDataPath();
        lp.pathString = s;
        lp.lpt = LocationDataPathTarget.getPathFor(s);
        if(lp.lpt != LocationDataPathTarget.INVALID){
            lp.location_id = s.substring(lp.lpt.getPath().length(),s.length()-1);
        }
        return lp;
    }

    @JsonValue
    public String toString(){
        return pathString;
    }

    public LocationDataPathTarget getLpt() {
        return lpt;
    }

    public String getLocation_id() {
        return location_id;
    }
}
