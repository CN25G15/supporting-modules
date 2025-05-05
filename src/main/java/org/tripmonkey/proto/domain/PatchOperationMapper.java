package org.tripmonkey.proto.domain;

import org.tripmonkey.patch.data.Operation;
import org.tripmonkey.rest.patch.fields.Op;

public class PatchOperationMapper extends ProtoMapper<Op, Operation> {

    @Override
    protected Operation serialize(Op d) {
        return switch (d){
            case ADD -> Operation.add;
            case REMOVE -> Operation.remove;
            case REPLACE -> Operation.replace;
            case MOVE -> Operation.move;
            case COPY -> Operation.copy;
            case TEST -> Operation.test;
            case INVALID -> Operation.unknown;
        };
    }

    @Override
    protected Op deserialize(Operation g) {
        return switch(g){
            case Operation.add -> Op.ADD;
            case Operation.remove -> Op.REMOVE;
            case Operation.replace -> Op.REPLACE;
            case Operation.move -> Op.MOVE;
            case Operation.copy -> Op.COPY;
            case Operation.test -> Op.TEST;
            default -> Op.INVALID;
        };
    }
}
