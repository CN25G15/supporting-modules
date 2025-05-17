package org.tripmonkey.rest.patch.fields.path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public class PathNode {

    public static PathNode INVALID = new PathNode(TargetType.INVALID, null);

    private TargetType targetType;
    private String target;
    private PathNode nextNode = null;

    private PathNode(TargetType tt, String target){
        this.targetType = tt;
        this.target = target;
    }

    static private PathNode from(PathNode parent, String[] path){

        TargetType t = Optional.ofNullable(parent)
                .map(pathNode -> switch(parent.targetType){
                    case LOC_LISTS -> TargetType.valueForLocList(path[0]);
                    case LOC_DATA -> TargetType.valueForLocData(path[0]);
                    default -> TargetType.INVALID;
                }).orElse(TargetType.valueForRoot(path[0]));

        if(TargetType.INVALID.equals(t))
            return new PathNode(t,null);

        String target = path.length >= 2 ? path[1] : null;
        PathNode pn = new PathNode(t, target);

        if(path.length >= 3){
            PathNode next = from(pn, Arrays.stream(path,3,path.length).toArray(String[]::new));
            if(TargetType.INVALID.equals(next.targetType)) {
                pn.targetType = TargetType.INVALID;
                pn.target = null;
            } else {
                pn.nextNode = next;
            }
        }

        return pn;
    }

    @JsonCreator
    static public PathNode from(String full_path){
        return from(null ,full_path.split("/"));
    }

    @JsonValue
    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append(this.targetType);
        if(this.target != null) {
            b.append("/");
            b.append(this.target);
        }
        if(this.nextNode != null){
            b.append("/");
            b.append(this.nextNode);
        }
        return b.toString();

    }

    public TargetType getTargetType() {
        return targetType;
    }

    public String getTarget() {
        return target;
    }

    public PathNode getNextNode() {
        return nextNode;
    }

    public boolean hasNextNode() {
        return nextNode != null;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(obj instanceof PathNode pn)
            return this.toString().equals(pn.toString());
        return false;
    }

}
