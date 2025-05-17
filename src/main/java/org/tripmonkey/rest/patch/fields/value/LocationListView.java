package org.tripmonkey.rest.patch.fields.value;

import org.tripmonkey.rest.domain.data.LocationListDTO;

public interface LocationListView {

    public LocationListDTO asLocationList();

    public boolean isLocationList();

}
