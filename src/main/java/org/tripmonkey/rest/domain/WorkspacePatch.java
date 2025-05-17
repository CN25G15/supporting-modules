package org.tripmonkey.rest.domain;

import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;


public class WorkspacePatch<ValueType> {

    private String workspace_id;
    private String user;
    private Op op;
    private PathNode path;
    private ValueType value;

    private WorkspacePatch(){
    }

    public WorkspacePatch(String wid, String uuid, Op op, PathNode path, ValueType value){
        this.workspace_id = wid;
        this.user = uuid;
        this.op = op;
        this.path = path;
        this.value = value;
    }

    public String getUser() {
        return user;
    }

    public Op getOp() {
        return op;
    }

    public PathNode getPath() {
        return path;
    }

    public ValueType getValue() {
        return value;
    }

    public String getWorkspaceId(){
        return workspace_id;
    }
}
