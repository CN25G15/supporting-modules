package org.tripmonkey.workspace.data.jsonpatch.applier;

import org.tripmonkey.workspace.data.Itinerary;
import org.tripmonkey.workspace.data.Workspace;
import org.tripmonkey.workspace.data.location.data.LocationData;

public interface PatchVisitor {

    void visitWorkspace(Workspace w) throws PatchException;
    void visitItinerary(Itinerary i) throws PatchException;
    void visitLocationData(LocationData locationData) throws PatchException;
}
