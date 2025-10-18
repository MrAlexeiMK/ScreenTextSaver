package ru.iot.screentextsaver.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record SendScreenMessagePayload(byte[] bytes) implements CustomPacketPayload {
    private static final ResourceLocation sendMessageChannel = ResourceLocation.fromNamespaceAndPath(
        // Mod ID.
        "screentextsaver",
        // name of resource.
        "send_message"
    );
    public static final Type<SendScreenMessagePayload> PACKET_TYPE =
        new Type<>(sendMessageChannel);
    public static final StreamCodec<FriendlyByteBuf, SendScreenMessagePayload> STREAM_CODEC =
        StreamCodec.ofMember(
            (payload, buffer) -> buffer.writeByteArray(payload.bytes()),
            buffer -> new SendScreenMessagePayload(buffer.readByteArray())
        );

    @Override
    @NotNull
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}
