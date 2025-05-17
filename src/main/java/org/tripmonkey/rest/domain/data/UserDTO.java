package org.tripmonkey.rest.domain.data;

import java.util.Optional;
import java.util.UUID;

public class UserDTO {

    UUID id;

    public static UserDTO from(UUID uuid) {
        UserDTO u = new UserDTO();
        u.id = uuid;
        return u;
    }

    public static Optional<UserDTO> from(String uuid) {
        Optional o;
        try {
            UUID id = UUID.fromString(uuid);
            o = Optional.of(from(id));
        } catch (IllegalArgumentException iae){
            o = Optional.empty();
        }
        return o;
    }

    public String toString(){
        return id.toString();
    }

}
