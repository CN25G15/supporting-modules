package org.tripmonkey.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    @JsonProperty
    private User u;
    @JsonProperty private String comment;

    public String getComment() {
        return comment;
    }

    public User getU() {
        return u;
    }


}
