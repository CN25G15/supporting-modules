package org.tripmonkey.workspace.patcher;

import org.tripmonkey.workspace.domain.Itinerary;
import org.tripmonkey.workspace.domain.Workspace;
import org.tripmonkey.workspace.domain.location.LocationData;

public interface PatchVisitor {

    void visitWorkspace(Workspace w) throws PatchException;
    void visitItinerary(Itinerary i) throws PatchException;
    void visitLocationData(LocationData locationData) throws PatchException;
}
