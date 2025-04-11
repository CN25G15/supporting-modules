package org.tripmonkey.workspace.domain;

import jakarta.json.bind.annotation.JsonbProperty;
import org.tripmonkey.workspace.patcher.PatchVisitor;
import org.tripmonkey.workspace.domain.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Itinerary implements TaggedObject {

    @JsonbProperty List<Location> locations = new ArrayList<>();

    public static Itinerary from(List<Location> odl) {
        Itinerary i = new Itinerary();
        i.locations = odl;
        return i;
    }

    @Override
    public boolean is(String tag) {
        return false;
    }

    @Override
    public List<String> getTags() {
        return List.of();
    }

    public void apply(PatchVisitor pv) {
        pv.visitItinerary(this);
    }

}
