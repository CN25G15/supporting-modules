package org.tripmonkey.rest.patch.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

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

    @JsonCreator
    public static Op forValue(String s) {
        return Arrays.stream(values()).filter(ot -> s.toLowerCase().equals(ot.op)).findFirst().orElse(INVALID);
    }

    @JsonValue
    public String toValue() {
        return this.op;
    }
}
