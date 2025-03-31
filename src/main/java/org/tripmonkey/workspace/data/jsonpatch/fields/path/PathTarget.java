package org.tripmonkey.workspace.data.jsonpatch.fields.path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class PathTarget {

    private String pathString;
    private OperationPath path;
    private String target;

    @JsonCreator
    public static PathTarget getTargetFor(String s) {
        PathTarget p = new PathTarget();
        p.pathString = s;
        p.path = OperationPath.getPathFor(s);
        if(p.path != OperationPath.INVALID){
            p.target = s.substring(p.path.getPath().length(),s.length()-1);
        }
        return p;
    }

    public OperationPath getPath(){
        return path;
    }

    public String getTarget() { return target; }

    @JsonValue
    public String toString(){
        return pathString;
    }

}
