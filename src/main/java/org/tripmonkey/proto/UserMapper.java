package org.tripmonkey.proto;

import org.tripmonkey.domain.data.User;

public class UserMapper extends ProtoSerde<User, org.tripmonkey.workspace.service.User> {
    @Override
    protected org.tripmonkey.workspace.service.User serialize(User d) {
        return org.tripmonkey.workspace.service.User.newBuilder()
                .setUserId(d.toString())
                .build();
    }

    @Override
    protected User deserialize(org.tripmonkey.workspace.service.User g) {
        return User.from(g.getUserId()).orElse(null);
    }
}
