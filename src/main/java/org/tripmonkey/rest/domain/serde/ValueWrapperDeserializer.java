package org.tripmonkey.rest.domain.serde;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.LocationMetadataDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.domain.value.ValueType;
import org.tripmonkey.rest.domain.value.ValueWrapper;

import java.lang.reflect.Type;

public class ValueWrapperDeserializer implements JsonbDeserializer<ValueWrapper> {

    @Override
    public ValueWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        ValueType vt =ValueType.INVALID;
        Object o = null;
        for(JsonParser.Event e = jsonParser.next(); jsonParser.hasNext(); e = jsonParser.next()) {
            if(JsonParser.Event.KEY_NAME.equals(e)) {
                String keyName = jsonParser.getString();
                if(keyName.equals("type")) {
                    String typeString = deserializationContext.deserialize(String.class,jsonParser);
                    vt = ValueType.forValue(typeString);
                }
                if(keyName.equals("value")) {
                    o = switch(vt){
                        case INVALID -> null;
                        case COMMENT -> deserializationContext.deserialize(CommentDTO.class,jsonParser);
                        case LOCATION -> deserializationContext.deserialize(LocationDTO.class,jsonParser);
                        case LOC_LIST -> deserializationContext.deserialize(LocationListDTO.class,jsonParser);
                        case LOC_META -> deserializationContext.deserialize(LocationMetadataDTO.class,jsonParser);
                        case USER -> deserializationContext.deserialize(UserDTO.class,jsonParser);
                    };
                }
            }
        }
        return ValueWrapper.from(vt,o);
    }
}
