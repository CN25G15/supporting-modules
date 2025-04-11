package org.tripmonkey.workspace.grpc.serde;

public abstract class GRPCSerde<DOMAIN,GRPC> {

    public UserSerde userSerde = new UserSerde();
    public CommentSerde commentSerde = new CommentSerde();
    public LocationSerde locationSerde = new LocationSerde();
    public LocationDataSerde locationDataSerde = new LocationDataSerde();
    public OperationSerde operationSerde = new OperationSerde();
    public LocationListHeaderSerde locationListHeaderSerde = new LocationListHeaderSerde();
    public JsonPatchSerde jsonPatchSerde = new JsonPatchSerde();
    public WorkspacePatchSerde workspacePatchSerde = new WorkspacePatchSerde();


    protected abstract GRPC serialize(DOMAIN d);

    protected abstract DOMAIN deserialize(GRPC g);

}
