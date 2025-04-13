package org.tripmonkey.rest.domain.serde;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import org.tripmonkey.rest.domain.data.UserDTO;

public class UserSerializer implements JsonbSerializer<UserDTO> {
    @Override
    public void serialize(UserDTO user, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.writeStartObject();
        jsonGenerator.write("id",user.toString());
        jsonGenerator.writeEnd();
    }
}
