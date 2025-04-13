package org.tripmonkey.rest.domain.data;

import jakarta.json.bind.annotation.JsonbProperty;

public class CommentDTO {

    @JsonbProperty String comment;

    public String getComment() {
        return comment;
    }

    public static CommentDTO from(String comment) {
        CommentDTO c = new CommentDTO();
        c.comment = comment;
        return c;
    }

}
