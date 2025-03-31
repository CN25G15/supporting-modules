package org.tripmonkey.workspace.data.location.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tripmonkey.workspace.data.TaggedObject;
import org.tripmonkey.workspace.data.jsonpatch.applier.PatchVisitor;

import java.util.ArrayList;
import java.util.List;

public class LocationData implements TaggedObject {

    @JsonProperty String description;
    @JsonProperty List<Comment> comments;
    @JsonProperty List<String> tags;

    public void apply(PatchVisitor pv){
        pv.visitLocationData(this);
    }

    public LocationData(){
        this.description = "";
        this.comments = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    @Override
    public boolean is(String tag) {
        return this.tags.contains(tag);
    }

    @Override
    public List<String> getTags() {
        return tags;
    }
}
