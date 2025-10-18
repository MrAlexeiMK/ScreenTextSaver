package ru.iot.screentextsaver.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import ru.iot.screentextsaver.client.listener.ClientPlayConnectionListener;
import ru.iot.screentextsaver.client.network.MessagePacketSenderImplementation;
import ru.iot.screentextsaver.network.SendScreenMessagePayload;

public class ScreenTextSaverClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register client join listener.
        final var messagePacketSender = new MessagePacketSenderImplementation();
        final var clientPlayConnectionListener = new ClientPlayConnectionListener(messagePacketSender);
        ClientPlayConnectionEvents.JOIN.register(clientPlayConnectionListener);
        // Register client-to-server payload.
        PayloadTypeRegistry.playC2S().register(
            SendScreenMessagePayload.PACKET_TYPE,
            SendScreenMessagePayload.STREAM_CODEC
        );
    }
}
