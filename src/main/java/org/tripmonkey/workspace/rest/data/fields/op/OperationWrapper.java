package org.tripmonkey.workspace.rest.data.fields.op;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class OperationWrapper {

    private String operationString;
    private OperationType op;

    @JsonCreator
    public static OperationWrapper getOperationTarget(@JsonProperty("op") String s){
        OperationWrapper opt = new OperationWrapper();
        opt.operationString = s;
        opt.op = OperationType.forValue(s);
        return opt;
    }

    @JsonValue
    public String toString(){
        return operationString;
    }

    public OperationType getType(){
        return this.op;
    }



}
