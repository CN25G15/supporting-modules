package org.tripmonkey.workspace.rest.data;

import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.rest.data.fields.op.OperationWrapper;
import org.tripmonkey.workspace.rest.data.fields.op.OperationType;
import org.tripmonkey.workspace.rest.data.fields.path.PathTarget;


public class Patch {

    @JsonbProperty OperationWrapper op;
    @JsonbProperty PathTarget path;
    @JsonbProperty Object value;

    public Object getValue(){
        return value;
    }

    public PathTarget getTarget() {
        return path;
    }

    public OperationType getOp(){
        return op.getType();
    }

    public static Patch of(OperationWrapper op, PathTarget path, Object ob) {
        Patch p = new Patch();
        p.op = op;
        p.path = path;
        p.value = ob;
        return p;
    }


}