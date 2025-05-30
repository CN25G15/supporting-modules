package org.tripmonkey.rest.patch;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.tripmonkey.proto.contents.data.CommentDTO;

@QuarkusTest
public class ProtoStringConversionTest {
/*
    @Test
    public void CommentConversion() throws InvalidProtocolBufferException {

        byte[] a = CommentDTO.newBuilder().setContents("Banana peel").build().toByteArray();

        System.out.println(CommentDTO.parseFrom(a).getContents());
    }
*/
}
