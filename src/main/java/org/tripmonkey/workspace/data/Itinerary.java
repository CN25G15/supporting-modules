package org.tripmonkey.workspace.data;

import org.tripmonkey.workspace.data.jsonpatch.applier.PatchVisitor;
import org.tripmonkey.workspace.data.location.Location;

import java.util.List;

public class Itinerary implements TaggedObject{


    public static Itinerary from(List<Location> odl) {
        return null;
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
