package org.tripmonkey.workspace.rest.data.fields.op;

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
        return Arrays.stream(values()).filter(ot -> s.toLowerCase().equals(ot.op)).findFirst().orElse(INVALID);
    }

    public String toValue() {
        return this.op;
    }
}
