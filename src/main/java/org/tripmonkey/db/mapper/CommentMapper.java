package org.tripmonkey.db.mapper;

import org.tripmonkey.db.data.CommentDB;
import org.tripmonkey.rest.domain.data.CommentDTO;

public class CommentMapper {

    public static CommentDB fromComment(CommentDTO cdto) {
        return CommentDB.of(cdto.getComment());
    }

    public static CommentDTO fromCommentDB(CommentDB cdb) {
        return CommentDTO.from(cdb.getComment());
    }

}
