package org.tripmonkey.rest.domain.serde;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import org.tripmonkey.rest.domain.WorkspacePatch;
import org.tripmonkey.rest.domain.value.ValueWrapper;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;

import java.lang.reflect.Type;

public class WorkspacePatchDeserializer implements JsonbDeserializer<WorkspacePatch> {
    @Override
    public WorkspacePatch deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {

        String wid = "None";
        String user = "None";
        Op op = Op.INVALID;
        PathNode path = PathNode.INVALID;
        ValueWrapper value = null;

        for(JsonParser.Event e = jsonParser.next(); jsonParser.hasNext(); e = jsonParser.next()) {
            if (JsonParser.Event.KEY_NAME.equals(e)) {
                String keyName = jsonParser.getString();
                switch(keyName){
                    case "wid" -> wid = deserializationContext.deserialize(String.class, jsonParser);
                    case "user" -> user = deserializationContext.deserialize(String.class, jsonParser);
                    case "op" -> op = deserializationContext.deserialize(Op.class, jsonParser);
                    case "path" -> path = deserializationContext.deserialize(PathNode.class, jsonParser);
                    case "value" -> value = deserializationContext.deserialize(ValueWrapper.class, jsonParser);
                }
            }
        }

        return WorkspacePatch.fromArgs(wid,user,op,path,value);
    }
}
