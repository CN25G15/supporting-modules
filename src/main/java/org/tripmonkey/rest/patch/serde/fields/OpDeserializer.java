package org.tripmonkey.rest.patch.serde.fields;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import org.tripmonkey.rest.patch.fields.Op;

import java.lang.reflect.Type;

public class OpDeserializer implements JsonbDeserializer<Op> {

    @Override
    public Op deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        Op op = Op.INVALID;

        if(!jsonParser.hasNext()){
            return op;
        }

        JsonParser.Event e = jsonParser.next();
        if(e.equals(JsonParser.Event.VALUE_STRING)){
            op = Op.forValue(jsonParser.getString());
        }

        return op;
    }

}
