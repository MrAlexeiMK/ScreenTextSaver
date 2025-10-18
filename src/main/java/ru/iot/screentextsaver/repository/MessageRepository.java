package ru.iot.screentextsaver.repository;

import ru.iot.screentextsaver.entity.MessageEntity;

public interface MessageRepository {
    void save(final MessageEntity message);
}
