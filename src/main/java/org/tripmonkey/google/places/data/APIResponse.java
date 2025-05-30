package org.tripmonkey.google.places.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse {

    @JsonProperty String error_message;
    @JsonProperty Place result;
    @JsonProperty ResponseStatus status;

    public Place getResult() {
        return result;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getErrorMessage(){ return error_message; }

    public static APIResponse newUnkown(String place_id) {
        APIResponse r = new APIResponse();
        r.error_message = null;
        r.result = Place.of(place_id);
        r.status = ResponseStatus.OK;
        return r;
    }
}
