package org.tripmonkey.workspace.data.location.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Comment {

    @JsonProperty UUID user;
    @JsonProperty String comment;

}
