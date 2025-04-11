package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.domain.User;

public final class UserSerde extends
        GRPCSerde<User, org.tripmonkey.patch.contents.data.User> {

    protected UserSerde(){}

    @Override
    public org.tripmonkey.patch.contents.data.User serialize(User d) {
        return org.tripmonkey.patch.contents.data.User
                .newBuilder()
                .setId(d.toString())
                .build();
    }

    @Override
    public User deserialize(org.tripmonkey.patch.contents.data.User g) {
        return User.from(g.getId());
    }
}
