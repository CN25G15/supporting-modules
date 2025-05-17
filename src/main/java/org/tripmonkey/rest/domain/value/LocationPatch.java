package org.tripmonkey.rest.domain.value;

import org.tripmonkey.domain.patch.PatchApplier;
import org.tripmonkey.domain.patch.PatchVisitor;
import org.tripmonkey.domain.patch.WorkspacePatchException;
import org.tripmonkey.rest.domain.WorkspacePatch;
import org.tripmonkey.rest.domain.data.LocationDTO;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class LocationPatch extends WorkspacePatch<LocationDTO> implements PatchVisitor {
    public LocationPatch(String wid, String uuid, Op op, PathNode path, LocationDTO value) {
        super(wid, uuid, op, path, value);
    }

    @Override
    public void visit(PatchApplier w) throws WorkspacePatchException {
        w.apply(this);
    }
}
