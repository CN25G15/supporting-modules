package org.tripmonkey.workspace.domain.location;


import jakarta.json.bind.annotation.JsonbNillable;
import org.tripmonkey.workspace.domain.TaggedObject;
import org.tripmonkey.workspace.patcher.PatchVisitor;

import java.util.ArrayList;
import java.util.List;

public class LocationData implements TaggedObject {

    @JsonbNillable String description;
    @JsonbNillable List<Comment> comments;
    @JsonbNillable List<String> tags;

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

    public void merge(LocationData ld) {
        this.description = ld.description != null ? ld.description : this.description;
        this.comments = ld.comments != null ? ld.comments : this.comments;
        this.tags = ld.tags != null ? ld.tags : this.tags;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public static LocationData of(String description,
                                  List<Comment> comment,
                                  List<String> tags){
        LocationData ld = new LocationData();
        ld.description = description;
        ld.comments = comment;
        ld.tags = tags;
        return ld;
    }
}
