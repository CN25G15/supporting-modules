package org.tripmonkey.rest.patch;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;
import org.tripmonkey.rest.patch.serde.PatchDeserializer;
import org.tripmonkey.rest.patch.serde.PatchSerializer;

@JsonbTypeSerializer(PatchSerializer.class)
@JsonbTypeDeserializer(PatchDeserializer.class)
public class Patch {

     Op op;
     PathNode path;
     Object value;

    public Op getOp() {
        return op;
    }

    public PathNode getPath() {
        return path;
    }

    public Object getValue() {
        return value;
    }

    public static Patch from(Op op, PathNode pn, Object v) {
        Patch p = new Patch();
        p.op = op;
        p.path = pn;
        p.value = v;
        return p;
    }
}
