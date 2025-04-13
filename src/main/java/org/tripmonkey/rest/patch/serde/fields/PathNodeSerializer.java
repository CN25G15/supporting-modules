package org.tripmonkey.rest.patch.serde.fields;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class PathNodeSerializer implements JsonbSerializer<PathNode> {

    @Override
    public void serialize(PathNode pathNode, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.write(pathNode.toString());
    }

}
