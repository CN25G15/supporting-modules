package org.tripmonkey.rest.patch.fields;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import org.tripmonkey.rest.patch.serde.fields.OpDeserializer;
import org.tripmonkey.rest.patch.serde.fields.OpSerializer;

import java.util.Arrays;

@JsonbTypeSerializer(OpSerializer.class)
@JsonbTypeDeserializer(OpDeserializer.class)
public enum Op {
    ADD("add"),
    REMOVE("remove"),
    REPLACE("replace"),
    MOVE("move"),
    COPY("copy"),
    TEST("test"),
    INVALID("unknown");

    private String op;

    Op(String s){
        this.op = s;
    }

    public static Op forValue(String s) {
        return Arrays.stream(values()).filter(ot -> s.toLowerCase().equals(ot.op)).findFirst().orElse(INVALID);
    }

    public String toValue() {
        return this.op;
    }
}
