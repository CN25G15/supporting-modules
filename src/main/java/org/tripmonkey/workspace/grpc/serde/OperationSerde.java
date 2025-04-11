package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.patch.data.Operation;
import org.tripmonkey.workspace.rest.data.fields.op.OperationType;

public final class OperationSerde extends
        GRPCSerde<OperationType, org.tripmonkey.patch.data.Operation> {

    protected OperationSerde(){}

    @Override
    public Operation serialize(OperationType d) {
        return switch(d) {
            case ADD -> Operation.add;
            case REMOVE -> Operation.remove;
            case REPLACE -> Operation.replace;
            case MOVE -> Operation.move;
            case COPY -> Operation.copy;
            case TEST -> Operation.test;
            case INVALID -> Operation.unknown;
        };    }

    @Override
    public OperationType deserialize(Operation g) {
        return switch(g) {
            case unknown -> OperationType.INVALID;
            case add -> OperationType.ADD;
            case remove -> OperationType.REMOVE;
            case replace -> OperationType.REPLACE;
            case move -> OperationType.MOVE;
            case copy -> OperationType.COPY;
            case test -> OperationType.TEST;
            case UNRECOGNIZED -> OperationType.INVALID;
        };
    }
}
