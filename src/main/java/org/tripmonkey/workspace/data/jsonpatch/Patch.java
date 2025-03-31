package org.tripmonkey.workspace.data.jsonpatch;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.workspace.data.User;
import org.tripmonkey.workspace.data.jsonpatch.fields.op.OperationWrapper;
import org.tripmonkey.workspace.data.jsonpatch.fields.op.OperationType;
import org.tripmonkey.workspace.data.jsonpatch.fields.path.PathTarget;

public class Patch<Content> {

    @JsonProperty User agent;
    @JsonProperty OperationWrapper op;
    @JsonProperty PathTarget path;
    @JsonProperty Content value;

    public Content getContent() {
        return value;
    }

    public PathTarget getTarget() {
        return path;
    }

    public OperationType getOp(){
        return op.getType();
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }
}