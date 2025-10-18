package ru.iot.screentextsaver.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iot.screentextsaver.MessageProto;
import ru.iot.screentextsaver.network.SendScreenMessagePayload;

import static java.util.Objects.requireNonNull;

/**
 * Default implementation of {@link MessagePacketSender}.
 */
public class MessagePacketSenderImplementation implements MessagePacketSender {
    private static final Logger logger = LoggerFactory.getLogger(MessagePacketSenderImplementation.class);

    @Override
    public void sendToServer(final String screenText) {
        requireNonNull(screenText, "screenText could not be null");
        logger.info("Send message packet to server: {}", screenText);
        final MessageProto.Message message = MessageProto.Message.newBuilder()
                                                                 .setText(screenText)
                                                                 .build();
        final var payload = new SendScreenMessagePayload(message.toByteArray());
        ClientPlayNetworking.send(payload);
    }
}
