package org.tripmonkey.proto.domain;

import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.domain.value.ValueType;
import org.tripmonkey.rest.domain.value.ValueWrapper;

public class ValueWrapperMapper extends ProtoMapper<ValueWrapper, org.tripmonkey.patch.data.ValueWrapper> {

    @Override
    protected org.tripmonkey.patch.data.ValueWrapper serialize(ValueWrapper d) {
        org.tripmonkey.patch.data.ValueWrapper.Builder b = org.tripmonkey.patch.data.ValueWrapper.newBuilder()
                .setType(d.getType().toString());
        switch (d.getType()){
            case INVALID,LOC_META -> {}
            case COMMENT -> b.setComment(commentSerde.serialize((CommentDTO) d.getValue()));
            case LOCATION -> b.setLocation(locationSerde.serialize((LocationDTO) d.getValue()));
            case LOC_LIST -> b.setLocationlist(locationListDTOMapper.serialize((LocationListDTO) d.getValue()));
            case USER -> b.setUser(userSerde.serialize((UserDTO) d.getValue()));
        };

        return b.build();
    }

    @Override
    protected ValueWrapper deserialize(org.tripmonkey.patch.data.ValueWrapper g) {
        ValueType vt = ValueType.INVALID;
        Object o = null;
        switch(g.getValueCase()){
            case COMMENT -> {
                vt = ValueType.COMMENT;
                o = commentSerde.deserialize(g.getComment());
            }
            case LOCATION -> {
                vt = ValueType.LOCATION;
                o = locationSerde.deserialize(g.getLocation());
            }
            case LOCATIONLIST -> {
                vt = ValueType.LOC_LIST;
                o = locationListDTOMapper.deserialize(g.getLocationlist());
            }
            case LOCATIONMETADATA -> {
                // Do nothing because unimplemented
            }
            case USER -> {
                vt = ValueType.USER;
                o = userSerde.deserialize(g.getUser());
            }
        }
        return ValueWrapper.from(vt,o);
    }
}
