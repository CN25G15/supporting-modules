package org.tripmonkey.workspace.domain.location;


import jakarta.json.bind.annotation.JsonbProperty;

public class Location {

    @JsonbProperty String place_id;

    public static Location from(String id){
        Location l = new Location();
        l.place_id = id;
        return l;
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

    @Override
    public String toString() {
        return place_id;
    }
}
