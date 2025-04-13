package org.tripmonkey.proto.mapper;

import org.tripmonkey.rest.domain.data.UserDTO;

public class UserDTOMapper extends ProtoSerde<UserDTO, org.tripmonkey.proto.contents.data.UserDTO> {


    @Override
    protected org.tripmonkey.proto.contents.data.UserDTO serialize(UserDTO d) {
        return org.tripmonkey.proto.contents.data.UserDTO.newBuilder()
                .setUserId(d.toString())
                .build();
    }

    @Override
    protected UserDTO deserialize(org.tripmonkey.proto.contents.data.UserDTO g) {
        return UserDTO.from(g.getUserId()).orElse(null);
    }
}
