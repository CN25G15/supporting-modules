package org.tripmonkey.db.data;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

@MongoEntity(collection = "workspaces")
public class WorkspaceDB {

    @BsonId private String id;
    @BsonProperty private List<String> users;
    @BsonProperty private List<WorkspacePatchDB> history;

    public String getId() {
        return id;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<WorkspacePatchDB> getHistory() {
        return history;
    }
}
