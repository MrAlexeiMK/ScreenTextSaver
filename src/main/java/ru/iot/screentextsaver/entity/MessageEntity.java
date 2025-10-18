package ru.iot.screentextsaver.entity;

import com.google.common.base.MoreObjects;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private UUID uuid;

    @Column(length = 256, nullable = false)
    private String text;

    protected MessageEntity() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("uuid", uuid)
                          .add("text", text)
                          .toString();
    }

    public static MessageEntity from(final UUID playerUuid, final String screenText) {
        final var messageEntity = new MessageEntity();
        messageEntity.uuid = playerUuid;
        messageEntity.text = screenText;
        return messageEntity;
    }
}
