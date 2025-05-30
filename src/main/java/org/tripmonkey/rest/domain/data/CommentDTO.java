package org.tripmonkey.rest.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDTO {

    @JsonProperty
    String comment;

    public String getComment() {
        return comment;
    }

    public static CommentDTO from(String comment) {
        CommentDTO c = new CommentDTO();
        c.comment = comment;
        return c;
    }

}
