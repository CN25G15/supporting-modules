package org.tripmonkey.rest.domain.serde;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import org.tripmonkey.rest.domain.WorkspacePatch;


public class WorkspacePatchSerializer implements JsonbSerializer<WorkspacePatch> {

    @Override
    public void serialize(WorkspacePatch workspacePatch, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.writeStartObject();
        serializationContext.serialize("wid",workspacePatch.getWorkspaceId(),jsonGenerator);
        serializationContext.serialize("user",workspacePatch.getUser(),jsonGenerator);
        serializationContext.serialize("op",workspacePatch.getOp(),jsonGenerator);
        serializationContext.serialize("path",workspacePatch.getPath(),jsonGenerator);
        serializationContext.serialize("value", workspacePatch.getValue(), jsonGenerator);
        jsonGenerator.writeEnd();
    }
}
