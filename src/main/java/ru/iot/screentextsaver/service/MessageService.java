package ru.iot.screentextsaver.service;

import java.util.UUID;

public interface MessageService {
    void saveMessage(UUID playerUuid, String screenText);
}
