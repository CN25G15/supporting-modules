package org.tripmonkey.db.mapper;

import org.tripmonkey.db.data.LocationDB;
import org.tripmonkey.rest.domain.data.LocationDTO;

public class LocationMapper {

    public static LocationDB of(LocationDTO ldto) {
        return LocationDB.of(ldto.getPlace_id());
    }

    public static LocationDTO of(LocationDB ldb) {
        return LocationDTO.from(ldb.getPlaceId());
    }

}
