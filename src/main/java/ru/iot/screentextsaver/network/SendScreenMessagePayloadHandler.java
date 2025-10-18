package ru.iot.screentextsaver.network;

import com.google.protobuf.InvalidProtocolBufferException;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayPayloadHandler;
import ru.iot.screentextsaver.MessageProto;
import ru.iot.screentextsaver.service.MessageService;

import static java.util.Objects.requireNonNull;

public class SendScreenMessagePayloadHandler implements PlayPayloadHandler<SendScreenMessagePayload> {
    private final MessageService messageService;

    public SendScreenMessagePayloadHandler(final MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void receive(final SendScreenMessagePayload payload,
                        final Context context) {
        requireNonNull(payload, "payload could not be null");
        requireNonNull(context, "context could not be null");
        try {
            final var playerUuid = context.player().getUUID();
            final var message = MessageProto.Message.parseFrom(payload.bytes());
            final var screenText = message.getText();
            messageService.saveMessage(playerUuid, screenText);
        } catch (final InvalidProtocolBufferException ex) {
            throw new RuntimeException("Could not process received screen message.", ex);
        }
    }
}
