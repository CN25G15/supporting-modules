package org.tripmonkey.rest.patch.fields.value;

import java.util.Arrays;

public enum ValueType {

    INVALID("unknown"),
    COMMENT("comment"),
    LOCATION("location"),
    LOC_LIST("loc_list"),
    LOC_META("loc_meta"),
    USER("user");

    private String type;

    ValueType(String type) {
        this.type = type;
    }

    public static ValueType forValue(String s) {
        return Arrays.stream(values()).filter(vt -> s.toLowerCase().equals(vt.type)).findFirst().orElse(INVALID);
    }

    public String toString(){
        return this.type;
    }

}
