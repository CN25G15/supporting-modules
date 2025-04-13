package org.tripmonkey.rest.patch.serde;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.LocationMetadataDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.patch.Patch;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PatchDeserializer implements JsonbDeserializer<Patch> {

    private Object unpackMap(Map<String, Object> map){
        if(map.containsKey("comment")){
            return CommentDTO.from((String) map.get("comment"));
        }
        if(map.containsKey("place_id")){
            return LocationDTO.from((String) map.get("place_id"));
        }
        if(map.containsKey("name") || map.containsKey("locations")) {
            String name = map.containsKey("name") ? (String) map.get("name") : null;
            String[] locations = map.containsKey("locations") ? (String[]) map.get("locations") : null;
            return LocationListDTO.fromStringList(name, Optional.ofNullable(locations).map(Arrays::asList).orElse(List.of()));
        }
        if(map.containsKey("description") || map.containsKey("tags")) {
            String name = map.containsKey("description") ? (String) map.get("description") : null;
            String[] tags = map.containsKey("tags") ? (String[]) map.get("tags") : null;
            return LocationMetadataDTO.from(name, Optional.ofNullable(tags).map(Arrays::asList).orElse(List.of()));
        }
        if(map.containsKey("id")) {
            return UserDTO.from((String) map.get("id"));
        }
        return null;
    }

    private Object deserializeValue(JsonParser jsonParser, DeserializationContext deserializationContext, Type type){
        Map<String, Object> objects = new HashMap<String, Object>();
        for(JsonParser.Event e = jsonParser.next(); jsonParser.hasNext(); e = jsonParser.next()) {
            if(JsonParser.Event.KEY_NAME.equals(e)) {
                String keyName = jsonParser.getString();
                switch(keyName) {
                    case "comment","place_id","name","description","id" -> {
                        objects.put(keyName, jsonParser.getString());
                    }
                    case "tags", "locations" -> {
                        objects.put(keyName, deserializationContext.deserialize(String[].class,jsonParser));
                    }
                }
            }
        }
        return unpackMap(objects);
    }

    @Override
    public Patch deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        Op op = Op.INVALID;
        PathNode pn = PathNode.INVALID;
        Object o = null;

        Map<String, Object> objects;

        for(JsonParser.Event e = jsonParser.next(); jsonParser.hasNext(); e = jsonParser.next()) {
            if(JsonParser.Event.KEY_NAME.equals(e)) {
                String keyName = jsonParser.getString();
                if(keyName.equals("op"))
                    op = deserializationContext.deserialize(Op.class, jsonParser);
                if(keyName.equals("path"))
                    pn = deserializationContext.deserialize(PathNode.class, jsonParser);
                if(keyName.equals("value")) {
                    o = deserializeValue(jsonParser,deserializationContext,type);
                }
            }
        }
        return Patch.from(op,pn,o);
    }
}
