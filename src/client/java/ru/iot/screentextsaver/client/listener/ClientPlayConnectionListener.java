package ru.iot.screentextsaver.client.listener;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Join;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import ru.iot.screentextsaver.client.SimpleTextSendScreen;
import ru.iot.screentextsaver.client.network.MessagePacketSender;

import static java.util.Objects.requireNonNull;

public record ClientPlayConnectionListener(
    MessagePacketSender messagePacketSender
) implements Join {

    @Override
    public void onPlayReady(final ClientPacketListener handler,
                            final PacketSender sender,
                            final Minecraft client) {
        requireNonNull(handler, "handler could not be null");
        requireNonNull(sender, "sender could not be null");
        requireNonNull(client, "client could not be null");
        client.execute(() -> {
            final var textSendScreen = new SimpleTextSendScreen(messagePacketSender);
            Minecraft.getInstance().setScreen(textSendScreen);
        });
    }
}
