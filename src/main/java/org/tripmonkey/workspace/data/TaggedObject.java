package org.tripmonkey.workspace.data;

import java.util.List;

public interface TaggedObject {

    boolean is(String tag);

    List<String> getTags();

}
