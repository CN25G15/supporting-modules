package org.tripmonkey.rest.domain.serde;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.LocationMetadataDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.domain.value.ValueWrapper;

public class ValueWrapperSerializer implements JsonbSerializer<ValueWrapper> {

    @Override
    public void serialize(ValueWrapper valueWrapper, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.writeStartObject();
        jsonGenerator.write("type",valueWrapper.getType().toString());
        switch(valueWrapper.getType()){
            case INVALID -> {}
            case COMMENT -> serializationContext.serialize("value", (CommentDTO)valueWrapper.getValue(),jsonGenerator);
            case LOCATION -> serializationContext.serialize("value", (LocationDTO)valueWrapper.getValue(),jsonGenerator);
            case LOC_LIST -> serializationContext.serialize("value", (LocationListDTO)valueWrapper.getValue(),jsonGenerator);
            case LOC_META -> serializationContext.serialize("value", (LocationMetadataDTO)valueWrapper.getValue(),jsonGenerator);
            case USER -> serializationContext.serialize("value", (UserDTO)valueWrapper.getValue(),jsonGenerator);
        }
        jsonGenerator.writeEnd();
    }

}
