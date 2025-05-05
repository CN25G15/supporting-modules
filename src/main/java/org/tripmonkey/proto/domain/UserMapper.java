package org.tripmonkey.proto.domain;

import org.tripmonkey.domain.data.User;

public class UserMapper extends ProtoMapper<User, org.tripmonkey.workspace.service.User> {
    @Override
    public org.tripmonkey.workspace.service.User serialize(User d) {
        return org.tripmonkey.workspace.service.User.newBuilder()
                .setUserId(d.toString())
                .build();
    }

    @Override
    public User deserialize(org.tripmonkey.workspace.service.User g) {
        return User.from(g.getUserId()).orElse(null);
    }
}
