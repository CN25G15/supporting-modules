package org.tripmonkey.google.places.data;

import jakarta.json.bind.annotation.JsonbProperty;

public class APIResponse {

    @JsonbProperty Place result;
    @JsonbProperty ResponseStatus status;

    public Place getResult() {
        return result;
    }

    public ResponseStatus getStatus() {
        return status;
    }
}
