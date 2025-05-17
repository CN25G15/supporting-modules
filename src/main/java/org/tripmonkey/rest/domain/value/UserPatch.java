package org.tripmonkey.rest.domain.value;

import org.tripmonkey.domain.patch.PatchApplier;
import org.tripmonkey.domain.patch.PatchVisitor;
import org.tripmonkey.domain.patch.WorkspacePatchException;
import org.tripmonkey.rest.domain.WorkspacePatch;
import org.tripmonkey.rest.domain.data.UserDTO;
import org.tripmonkey.rest.patch.fields.Op;
import org.tripmonkey.rest.patch.fields.path.PathNode;

public class UserPatch extends WorkspacePatch<UserDTO> implements PatchVisitor {

    public UserPatch(String wid, String uuid, Op op, PathNode path, UserDTO value) {
        super(wid, uuid, op, path, value);
    }

    @Override
    public void visit(PatchApplier w) throws WorkspacePatchException {
        w.apply(this);
    }
}
