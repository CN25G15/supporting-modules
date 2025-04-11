package org.tripmonkey.workspace.domain.location;

import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.domain.User;

public class LocationListHeader {

    @JsonbProperty String name;
    @JsonbProperty User user;

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public static LocationListHeader of(String name, User user){
        LocationListHeader llh = new LocationListHeader();
        llh.name = name;
        llh.user = user;
        return llh;
    }
}
