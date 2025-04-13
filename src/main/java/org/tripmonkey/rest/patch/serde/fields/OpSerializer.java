package org.tripmonkey.rest.patch.serde.fields;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import org.tripmonkey.rest.patch.fields.Op;

public class OpSerializer implements JsonbSerializer<Op> {

    @Override
    public void serialize(Op op, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.write(op.toValue());
    }

}
