package org.tripmonkey.proto.kafka;

import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.tripmonkey.notification.service.Notification;

public class NotificationSerializer extends ByteArraySerializer {

    public byte[] serialize(String topic, Notification notif) {
        return super.serialize(topic, notif.toByteArray());
    }

}
