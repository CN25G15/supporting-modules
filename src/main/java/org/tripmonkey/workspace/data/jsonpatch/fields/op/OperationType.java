package org.tripmonkey.workspace.data.jsonpatch.fields.op;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum OperationType {
    ADD("add"),
    REMOVE("remove"),
    REPLACE("replace"),
    MOVE("move"),
    COPY("copy"),
    TEST("test"),
    INVALID(null);

    private String op;

    OperationType(String s){
        this.op = s;
    }

    public static OperationType forValue(String s) {
        return Arrays.stream(values()).filter(ot -> s.equals(ot.op)).findFirst().orElse(INVALID);
    }

    public String toValue() {
        return this.op;
    }
}
