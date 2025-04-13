package org.tripmonkey.proto.mapper;

import org.tripmonkey.rest.domain.data.CommentDTO;

public class CommentDTOMapper extends ProtoSerde<CommentDTO, org.tripmonkey.proto.contents.data.CommentDTO> {

    @Override
    protected org.tripmonkey.proto.contents.data.CommentDTO serialize(CommentDTO d) {
        return org.tripmonkey.proto.contents.data.CommentDTO.newBuilder()
                .setContents(d.getComment()).build();
    }

    @Override
    protected CommentDTO deserialize(org.tripmonkey.proto.contents.data.CommentDTO g) {
        return CommentDTO.from(g.getContents());
    }
}
