package org.tripmonkey.rest.patch.fields.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.LocationMetadataDTO;
import org.tripmonkey.rest.domain.data.UserDTO;

import java.util.List;
import java.util.Optional;


public class SuperValueDTO implements UserView, CommentView,
        LocationView, LocationListView, LocationMetadataView {

    @JsonProperty String comment;
    @JsonProperty String place_id;
    @JsonProperty String name;
    @JsonProperty List<String> locations;
    @JsonProperty String description;
    @JsonProperty List<String> tags;
    @JsonProperty String user_id;

    @Override
    public CommentDTO asComment() {
        return CommentDTO.from(comment);
    }

    @Override
    public boolean isComment() {
        return comment != null && place_id == null && name == null && locations == null && description == null
                && tags == null && user_id == null;
    }

    @Override
    public LocationListDTO asLocationList() {
        return LocationListDTO.fromStringList(name, locations);
    }

    @Override
    public boolean isLocationList() {
        return comment == null && place_id == null && name != null && locations != null && description == null
                && tags == null && user_id == null;
    }

    @Override
    public LocationMetadataDTO asLocationMetadata() {
        return LocationMetadataDTO.from(description,tags);
    }

    @Override
    public boolean isLocationMetadata() {
        return comment == null && place_id == null && name == null && locations == null && description != null
                && tags != null && user_id == null;
    }

    @Override
    public LocationDTO asLocation() {
        return LocationDTO.from(place_id);
    }

    @Override
    public boolean isLocation() {
        return comment == null && place_id != null && name == null && locations == null && description == null
                && tags == null && user_id == null;
    }

    @Override
    public Optional<UserDTO> asUser() {
        return UserDTO.from(user_id);
    }

    @Override
    public boolean isUser() {
        return comment == null && place_id == null && name == null && locations == null && description == null
                && tags == null && user_id != null;
    }

    public ValueType getType(){
        if(isComment())
            return ValueType.COMMENT;
        if(isUser())
            return ValueType.USER;
        if(isLocation())
            return ValueType.LOCATION;
        if(isLocationList())
            return  ValueType.LOCATION;
        if(isLocationMetadata())
            return ValueType.LOC_META;
        return ValueType.INVALID;
    }

    public String toString() {
        StringBuilder stb = new StringBuilder().append("{");
        switch(getType()){
            default -> {}
            case COMMENT -> {
                stb.append(String.format("\"comment\":\"%s\"",comment));
            }
            case LOCATION -> {
                stb.append(String.format("\"place_id\":\"%s\"",place_id));
            }
            case LOC_LIST -> {
                if(name != null)
                    stb.append(String.format("\"name\":\"%s\"",name));
                if(locations != null)
                    stb.append(String.format("\"locations\":\"[%s]\"",
                            String.join(",",locations.stream().map(s ->
                                    String.format("{\"place_id\":\"%s\"}",s)).toList())));
            }
            case LOC_META -> {
                if(description != null)
                    stb.append(String.format("\"description\":\"%s\"",description));
                if(tags != null)
                    stb.append(String.format("\"tags\":\"[%s]\"",
                            String.join(",", tags)));
            }
            case USER -> {
                stb.append(String.format("\"user_id\":\"%s\"",user_id));
            }
        }
        return stb.append("}").toString();
    }

}
