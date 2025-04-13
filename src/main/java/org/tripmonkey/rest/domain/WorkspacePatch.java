package org.tripmonkey.rest.domain;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import org.tripmonkey.rest.domain.data.CommentDTO;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.domain.data.LocationListDTO;
import org.tripmonkey.rest.domain.data.LocationMetadataDTO;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.domain.serde.WorkspacePatchDeserializer;
import org.tripmonkey.rest.domain.serde.WorkspacePatchSerializer;
import org.tripmonkey.rest.domain.value.ValueType;
import org.tripmonkey.rest.domain.value.ValueWrapper;
import org.tripmonkey.rest.patch.Patch;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;


@JsonbTypeSerializer(WorkspacePatchSerializer.class)
@JsonbTypeDeserializer(WorkspacePatchDeserializer.class)
public class WorkspacePatch {

    private String workspace_id;
    private String user;
    private Op op;
    private PathNode path;
    private ValueWrapper value;

    public static WorkspacePatch from(String wid, String uuid, Patch p){
        WorkspacePatch wp =  new WorkspacePatch();
        wp.user = uuid;
        wp.op = p.getOp();
        wp.path = p.getPath();
        ValueType vt = switch(p.getValue()){
            case CommentDTO c -> ValueType.COMMENT;
            case LocationDTO l -> ValueType.LOCATION;
            case LocationListDTO ll -> ValueType.LOCATION;
            case LocationMetadataDTO lm -> ValueType.LOC_META;
            case UserDTO u -> ValueType.USER;
            default -> ValueType.INVALID;
        };
        wp.value = ValueWrapper.from(vt, p.getValue());
        return wp;
    }

    public static WorkspacePatch fromArgs(String wid, String uuid, Op op, PathNode path, ValueWrapper value){
        WorkspacePatch wp =  new WorkspacePatch();
        wp.user = uuid;
        wp.op = op;
        wp.path = path;
        wp.value = value;
        return wp;
    }

    public String getUser() {
        return user;
    }

    public Op getOp() {
        return op;
    }

    public PathNode getPath() {
        return path;
    }

    public ValueWrapper getValue() {
        return value;
    }

    public String getWorkspaceId(){
        return workspace_id;
    }
}
