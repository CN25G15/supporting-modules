package org.tripmonkey.rest.patch.fields.value;

import org.tripmonkey.rest.domain.data.UserDTO;

import java.util.Optional;

public interface UserView {

    public Optional<UserDTO> asUser();

    public boolean isUser();

}
