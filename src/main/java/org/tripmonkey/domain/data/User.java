package org.tripmonkey.domain.data;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import org.tripmonkey.rest.domain.data.UserDTO;

import java.util.Optional;
import java.util.UUID;

public class User {

    @JsonbTransient private UUID id;
    @JsonbProperty private String user_id;

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

}
