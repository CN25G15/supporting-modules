package org.tripmonkey.rest.patch;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tripmonkey.rest.patch.fields.path.PathNode;
import org.tripmonkey.rest.patch.fields.path.TargetType;

@QuarkusTest
public class PatchPathTest {

    @Test
    void testValidEndpoints(){
        PathNode pn;
        pn = PathNode.from("collaborators");
        Assertions.assertEquals(TargetType.COLLABORATORS, pn.getTargetType());
    }

    //TODO valid subpath tests

    @Test
    void testInvalidEndpoints(){
        PathNode pn;
        pn = PathNode.from("bus_routes/1/stops/1");
        Assertions.assertEquals(TargetType.INVALID,pn.getTargetType());
    }

}
