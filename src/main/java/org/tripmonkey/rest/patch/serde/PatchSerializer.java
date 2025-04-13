package org.tripmonkey.rest.patch.serde;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.LocationMetadataDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.patch.Patch;

public class PatchSerializer implements JsonbSerializer<Patch> {

    @Override
    public void serialize(Patch patch, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.writeStartObject();
        serializationContext.serialize("op",patch.getOp(),jsonGenerator);
        serializationContext.serialize("path",patch.getPath(),jsonGenerator);
        switch(patch.getValue()){
            case UserDTO u -> serializationContext.serialize("value",u,jsonGenerator);
            case LocationDTO l -> serializationContext.serialize("value",l,jsonGenerator);
            case CommentDTO c -> serializationContext.serialize("value",c,jsonGenerator);
            case LocationListDTO ll -> serializationContext.serialize("value",ll,jsonGenerator);
            case LocationMetadataDTO lm -> serializationContext.serialize("value",lm,jsonGenerator);
            default -> {
            }
        }
        jsonGenerator.writeEnd();
    }

}
