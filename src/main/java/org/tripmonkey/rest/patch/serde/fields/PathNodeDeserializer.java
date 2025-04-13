package org.tripmonkey.rest.patch.serde.fields;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import org.tripmonkey.rest.patch.fields.path.PathNode;

import java.lang.reflect.Type;

public class PathNodeDeserializer implements JsonbDeserializer<PathNode> {

    @Override
    public PathNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        PathNode pn = null;

        if(!jsonParser.hasNext()){
            return pn;
        }

        JsonParser.Event e = jsonParser.next();
        if(e.equals(JsonParser.Event.VALUE_STRING)){
            pn = PathNode.from(jsonParser.getString());
        }

        return pn;
    }
}
