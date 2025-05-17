package org.tripmonkey.domain.patch;

import org.tripmonkey.rest.domain.value.CommentPatch;
import org.tripmonkey.rest.domain.value.LocationListPatch;
import org.tripmonkey.rest.domain.value.LocationMetadataPatch;
import org.tripmonkey.rest.domain.value.LocationPatch;
import org.tripmonkey.rest.domain.value.UserPatch;

public interface PatchApplier {

    public void apply(UserPatch up) throws WorkspacePatchException;
    public void apply(LocationPatch lp) throws WorkspacePatchException;
    public void apply(LocationListPatch llp) throws WorkspacePatchException;
    public void apply(CommentPatch cp) throws WorkspacePatchException;
    public void apply(LocationMetadataPatch lmp) throws WorkspacePatchException;

}
