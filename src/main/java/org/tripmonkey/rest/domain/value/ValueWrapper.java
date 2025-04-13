package org.tripmonkey.rest.domain.value;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import org.tripmonkey.rest.domain.serde.ValueWrapperDeserializer;
import org.tripmonkey.rest.domain.serde.ValueWrapperSerializer;

@JsonbTypeSerializer(ValueWrapperSerializer.class)
@JsonbTypeDeserializer(ValueWrapperDeserializer.class)
public class ValueWrapper {

    private ValueType type;
    private Object value;

    public static ValueWrapper from(ValueType vt, Object v){
        ValueWrapper vw = new ValueWrapper();
        vw.type = vt;
        vw.value = v;
        return vw;
    }

    public ValueType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
