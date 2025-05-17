package org.tripmonkey.rest.patch.fields.value;

import org.tripmonkey.rest.domain.data.LocationMetadataDTO;

public interface LocationMetadataView {

    public LocationMetadataDTO asLocationMetadata();

    public boolean isLocationMetadata();

}
