package org.tripmonkey.rest.domain.serde;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import org.tripmonkey.rest.domain.data.UserDTO;


import java.lang.reflect.Type;

public class UserDeserializer implements JsonbDeserializer<UserDTO> {

    @Override
    public UserDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {

        UserDTO u = null;

        for(JsonParser.Event e = jsonParser.next(); jsonParser.hasNext(); jsonParser.next()){
            if(e.equals(JsonParser.Event.KEY_NAME)){
                if(jsonParser.getString().equals("id"))
                    u = UserDTO.from(jsonParser.getString()).orElse(null);
            }
        }

        return u;
    }
}
