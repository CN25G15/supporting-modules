package org.tripmonkey.rest.patch;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;
import org.tripmonkey.rest.patch.fields.value.SuperValueDTO;
import org.tripmonkey.rest.patch.fields.value.ValueType;

public class Patch {

     @JsonProperty Op op;
     @JsonProperty PathNode path;
     @JsonProperty SuperValueDTO value;

    public Op getOp() {
        return op;
    }

    public PathNode getPath() {
        return path;
    }

    public SuperValueDTO getValue() {
        return value;
    }

    public static Patch from(Op op, PathNode pn, SuperValueDTO v) {
        Patch p = new Patch();
        p.op = op;
        p.path = pn;
        p.value = v;
        return p;
    }

    public boolean isValid() {
        return !Op.INVALID.equals(op)
                && !PathNode.INVALID.equals(path)
                && !ValueType.INVALID.equals(value.getType());
    }

    @Override
    public String toString() {
        return String.format("{\"op\":\"%s\",", op.toString()) +
                String.format("\"path\":\"%s\",", path.toString()) +
                String.format("\"value\":%s}", value.toString());
    }
}
