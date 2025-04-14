package org.tripmonkey.proto;

import org.tripmonkey.proto.contents.data.UserDTO;

public class UserDTOMapper extends ProtoSerde<org.tripmonkey.rest.domain.data.UserDTO, UserDTO> {


    @Override
    protected UserDTO serialize(org.tripmonkey.rest.domain.data.UserDTO d) {
        return UserDTO.newBuilder()
                .setUserId(d.toString())
                .build();
    }

    @Override
    protected org.tripmonkey.rest.domain.data.UserDTO deserialize(UserDTO g) {
        return org.tripmonkey.rest.domain.data.UserDTO.from(g.getUserId()).orElse(null);
    }
}
