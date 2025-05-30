package org.tripmonkey.google.places.data;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ResponseStatus {
    OK,
    REQUEST_DENIED,
    INVALID_REQUEST,
    @JsonEnumDefaultValue UNKNOWN
}
