package org.tripmonkey.proto.map;

import org.tripmonkey.proto.contents.data.CommentDTO;

public class CommentDTOMapper extends ProtoMapper<org.tripmonkey.rest.domain.data.CommentDTO, CommentDTO> {

    @Override
    protected CommentDTO serialize(org.tripmonkey.rest.domain.data.CommentDTO d) {
        return CommentDTO.newBuilder()
                .setContents(d.getComment()).build();
    }

    @Override
    protected org.tripmonkey.rest.domain.data.CommentDTO deserialize(CommentDTO g) {
        return org.tripmonkey.rest.domain.data.CommentDTO.from(g.getContents());
    }
}
