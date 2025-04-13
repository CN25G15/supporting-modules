package org.tripmonkey.domain.data;

import jakarta.json.bind.annotation.JsonbProperty;

public class Comment {

    @JsonbProperty private User u;
    @JsonbProperty private String comment;

    public String getComment() {
        return comment;
    }

    public User getU() {
        return u;
    }


}
