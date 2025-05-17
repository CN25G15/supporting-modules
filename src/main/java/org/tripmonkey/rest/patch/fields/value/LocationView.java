package org.tripmonkey.rest.patch.fields.value;

import org.tripmonkey.rest.domain.data.LocationDTO;

public interface LocationView {

    public LocationDTO asLocation();

    public boolean isLocation();

}
