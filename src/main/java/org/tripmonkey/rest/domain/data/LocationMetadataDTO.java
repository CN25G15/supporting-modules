package org.tripmonkey.rest.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationMetadataDTO {

    @JsonProperty
    String description;
    @JsonProperty List<String> tags;

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public static LocationMetadataDTO from(String description, List<String> tags) {
        LocationMetadataDTO lm = new LocationMetadataDTO();
        lm.description = description;
        lm.tags = tags;
        return lm;
    }

}
