package org.tripmonkey.proto;

public abstract class ProtoSerde<DOMAIN, PROTO> {

    public static UserDTOMapper userSerde = new UserDTOMapper();
    public static CommentDTOMapper commentSerde = new CommentDTOMapper();
    public static LocationDTOMapper locationSerde = new LocationDTOMapper();
    public static PatchOperationMapper patchOperationMapper = new PatchOperationMapper();
    public static LocationListDTOMapper locationListDTOMapper = new LocationListDTOMapper();
    public static ValueWrapperMapper valueWrapperMapper = new ValueWrapperMapper();
    public static LocationMapper locationMapper = new LocationMapper();
    public static UserMapper userMapper = new UserMapper();
    public static LocationListMapper locationListMapper = new LocationListMapper();
    public static WorkspaceMapper workspaceMapper = new WorkspaceMapper();
    public static WorkspacePatchMapper workspacePatchMapper = new WorkspacePatchMapper();

    protected abstract PROTO serialize(DOMAIN d);

    protected abstract DOMAIN deserialize(PROTO g);

}
