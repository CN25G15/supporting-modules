package org.tripmonkey.workspace.domain;

import java.util.List;

public interface TaggedObject {

    boolean is(String tag);

    List<String> getTags();

}
