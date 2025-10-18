package ru.iot.screentextsaver.service;

import ru.iot.screentextsaver.entity.MessageEntity;
import ru.iot.screentextsaver.repository.MessageRepository;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class MessageServiceImplementation implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImplementation(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void saveMessage(final UUID playerUuid, final String screenText) {
        requireNonNull(playerUuid, "playerUuid could not be null");
        requireNonNull(screenText, "screenText could not be null");
        final var messageEntity = MessageEntity.from(playerUuid, screenText);
        messageRepository.save(messageEntity);
    }
}
