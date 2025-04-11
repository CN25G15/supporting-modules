package org.tripmonkey.workspace.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.UUID;

public class User {

    UUID id;

    @JsonCreator
    public static User from(String id){
        User u = new User();
        try{
            u.id = UUID.fromString(id);
        } catch (IllegalArgumentException e){
            u.id = null;
        }
        return u;
    }

    @JsonValue
    public String toString(){
        return this.id.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) { // check if the object references are equal
            return true;
        }
        if (o instanceof User ou){
            return this.id.equals(ou.id);
        }
        return false;
    }
}
