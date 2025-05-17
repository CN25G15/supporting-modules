package org.tripmonkey.rest.patch.fields.value;

import org.tripmonkey.rest.domain.data.CommentDTO;

public interface CommentView {

    public CommentDTO asComment();

    public boolean isComment();

}
