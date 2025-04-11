package org.tripmonkey.workspace.grpc.serde;

import org.tripmonkey.workspace.domain.location.LocationData;

public final class LocationDataSerde extends
        GRPCSerde<LocationData, org.tripmonkey.patch.contents.data.LocationData> {

    protected LocationDataSerde(){}

    @Override
    public org.tripmonkey.patch.contents.data.LocationData serialize(LocationData d) {
        return org.tripmonkey.patch.contents.data.LocationData.newBuilder()
                .setDescription(d.getDescription())
                .addAllComments(d.getComments().stream()
                        .map(commentSerde::serialize).toList())
                .addAllTags(d.getTags())
                .build();
    }

    @Override
    public LocationData deserialize(org.tripmonkey.patch.contents.data.LocationData g) {
        return LocationData.of(
                g.getDescription(),
                g.getCommentsList().stream().map(commentSerde::deserialize).toList(),
                g.getTagsList()
        );
    }
}
