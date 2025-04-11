package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.domain.location.Comment;

public final class CommentSerde extends
        GRPCSerde<Comment, org.tripmonkey.patch.contents.data.Comment> {

    protected CommentSerde(){}

    @Override
    public org.tripmonkey.patch.contents.data.Comment serialize(Comment d) {
        return org.tripmonkey.patch.contents.data.Comment.newBuilder()
                .setUser(userSerde.serialize(d.getUser()))
                .setContents(d.getComment())
                .build();
    }

    @Override
    public Comment deserialize(org.tripmonkey.patch.contents.data.Comment g) {
        return Comment.of(userSerde.deserialize(g.getUser()), g.getContents());
    }
}
