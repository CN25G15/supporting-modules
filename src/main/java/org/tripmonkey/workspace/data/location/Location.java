package org.tripmonkey.workspace.data.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty String place_id;

    public Location(String id){
        this.place_id = id;
    }

    public String getId(){
        return place_id;
    }

    public boolean equals(Object o){
        if (this == o) { // check if the object references are equal
            return true;
        }
        if (o instanceof Location l){
            return this.place_id.equals(l.place_id);
        }
        return false;
    }

}
