package org.tripmonkey.domain.patch;

public interface PatchVisitor {

    public void visit(PatchApplier w) throws WorkspacePatchException;
}
