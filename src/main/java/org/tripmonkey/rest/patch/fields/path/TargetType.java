package org.tripmonkey.rest.patch.fields.path;

import java.util.List;

public enum TargetType {

    INVALID(null),
    COLLABORATORS("collaborators"),
    LOC_LISTS("location_lists"),
    ITINERARIES("itineraries"),
    LOC_DATA("location_data"),
    DESCRIPTION("description"),
    COMMENTS("comments"),
    TAGS("tags"),
    ITEM("item"),
    NAME("name"),
    LOCATIONS("locations");

    private String pathString;

    TargetType(String pathString){
        this.pathString = pathString;
    }

    private static List<TargetType> ROOT_NODES = List.of(COLLABORATORS,LOC_LISTS,ITINERARIES,LOC_DATA);
    private static List<TargetType> LOC_DATA_NODES = List.of(DESCRIPTION,COMMENTS,TAGS);
    private static List<TargetType> LOC_LIST_NODES = List.of(ITEM,NAME,LOCATIONS);

    private static TargetType valueFor(List<TargetType>tt, String value) {
        return tt.stream()
                .filter(targetType -> value.equals(targetType.toString()))
                .findFirst().orElse(INVALID);
    }

    public static TargetType valueForRoot(String value){
        return valueFor(ROOT_NODES, value);
    }

    public static TargetType valueForLocData(String value){
        return valueFor(LOC_DATA_NODES, value);
    }

    public static TargetType valueForLocList(String value){
        return valueFor(LOC_LIST_NODES, value);
    }

    @Override
    public String toString(){
        return this.pathString;
    }


}
