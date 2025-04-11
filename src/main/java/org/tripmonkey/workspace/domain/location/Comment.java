package org.tripmonkey.workspace.domain.location;

import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.domain.User;

public final class Comment {

    @JsonbProperty User user;
    @JsonbProperty String comment;

    protected Comment(){}

    public static Comment of(User commenter, String comment) {
        Comment c = new Comment();
        c.user = commenter;
        c.comment = comment;
        return c;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }
}
