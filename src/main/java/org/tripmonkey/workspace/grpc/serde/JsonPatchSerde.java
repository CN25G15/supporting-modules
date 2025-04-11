package org.tripmonkey.workspace.grpc.serde;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import org.tripmonkey.patch.data.JSONPatch;
import org.tripmonkey.workspace.domain.User;
import org.tripmonkey.workspace.domain.location.Comment;
import org.tripmonkey.workspace.domain.location.Location;
import org.tripmonkey.workspace.domain.location.LocationData;
import org.tripmonkey.workspace.domain.location.LocationList;
import org.tripmonkey.workspace.domain.location.LocationListHeader;
import org.tripmonkey.workspace.rest.data.Patch;
import org.tripmonkey.workspace.rest.data.fields.op.OperationWrapper;
import org.tripmonkey.workspace.rest.data.fields.path.PathTarget;

import java.util.Optional;

public class JsonPatchSerde extends
        GRPCSerde<Patch, org.tripmonkey.patch.data.JSONPatch>{

    protected JsonPatchSerde(){}

    private Optional<Message> toMessage(Object o) {
        return switch(o){
            case User u -> Optional.ofNullable(userSerde.serialize(u));
            case Comment c -> Optional.ofNullable(commentSerde.serialize(c));
            case Location l -> Optional.ofNullable(locationSerde.serialize(l));
            case LocationData ld -> Optional.ofNullable(locationDataSerde.serialize(ld));
            case LocationListHeader llh -> Optional.ofNullable(locationListHeaderSerde.serialize(llh));
            default -> Optional.empty();
        };
    }

    private Optional<Object> fromMessage(Message d) {
        return switch(d){
            case org.tripmonkey.patch.contents.data.User u -> Optional.ofNullable(userSerde.deserialize(u));
            case org.tripmonkey.patch.contents.data.Comment c -> Optional.ofNullable(commentSerde.deserialize(c));
            case org.tripmonkey.patch.contents.data.Location l -> Optional.ofNullable(locationSerde.deserialize(l));
            case org.tripmonkey.patch.contents.data.LocationData ld -> Optional.ofNullable(locationDataSerde.deserialize(ld));
            case org.tripmonkey.patch.contents.data.LocationListHeader llh-> Optional.ofNullable(locationListHeaderSerde.deserialize(llh));
            default -> Optional.empty();
        };
    }

    @Override
    protected JSONPatch serialize(Patch d) {
        return toMessage(d.getValue()).map(message -> JSONPatch.newBuilder()
                .setOp(operationSerde.serialize(d.getOp()))
                .setPath(d.getTarget().toString())
                .setValue(Any
                        .newBuilder()
                        .mergeFrom(message).build()).build()).orElse(null);
    }

    @Override
    protected Patch deserialize(JSONPatch g) {
        return fromMessage(g.getValue()).map(object ->
                Patch.of(
                        OperationWrapper.getOperationTarget(operationSerde.deserialize(g.getOp()).toValue()),
                        PathTarget.getTargetFor(g.getPath()),
                        object
                )
        ).orElse(null);
    }
}
