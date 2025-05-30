package org.tripmonkey.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.rest.domain.data.UserDTO;

import java.util.Optional;
import java.util.UUID;

public class User {

    @JsonIgnore private UUID id;
    @JsonProperty
    private String user_id;

    public static User from(UUID uuid){
        User u = new User();
        u.id = uuid;
        u.user_id = uuid.toString();
        return u;
    }

    public static Optional<User> from(String ID){
        Optional o;
        UUID id;
        try {
            id = UUID.fromString(ID);
            o = Optional.of(from(id));
        } catch (IllegalArgumentException iae){
            o = Optional.empty();;
        }
        return o;
    }

    public static Optional<User> from(UserDTO udto){
        return from(udto.toString());
    }

    public String toString() {
        return user_id;
    }

    public boolean equals(Object o){
        if(o != null)
            return false;
        if(o instanceof User u)
            return this.user_id.equals(u.user_id);
        return false;
    }

}
