package org.tripmonkey.rest.domain.data;

import jakarta.json.bind.annotation.JsonbNillable;

import java.util.List;

public class LocationMetadataDTO {

    @JsonbNillable String description;
    @JsonbNillable List<String> tags;

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
