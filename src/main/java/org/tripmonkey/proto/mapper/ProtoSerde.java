package org.tripmonkey.proto.mapper;

public abstract class ProtoSerde<DOMAIN, PROTO> {

    public static UserDTOMapper userSerde = new UserDTOMapper();
    public static CommentDTOMapper commentSerde = new CommentDTOMapper();
    public static LocationDTOMapper locationSerde = new LocationDTOMapper();
    public static PatchOperationMapper patchOperationMapper = new PatchOperationMapper();
    public static LocationListDTOMapper locationListDTOMapper = new LocationListDTOMapper();
    public static ValueWrapperMapper valueWrapperMapper = new ValueWrapperMapper();

    protected abstract PROTO serialize(DOMAIN d);

    protected abstract DOMAIN deserialize(PROTO g);

}
