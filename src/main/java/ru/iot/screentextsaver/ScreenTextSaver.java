package ru.iot.screentextsaver;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.iot.screentextsaver.config.ModConfig;
import ru.iot.screentextsaver.entity.MessageEntity;
import ru.iot.screentextsaver.network.SendScreenMessagePayload;
import ru.iot.screentextsaver.network.SendScreenMessagePayloadHandler;
import ru.iot.screentextsaver.repository.MessageRepositoryImplementation;
import ru.iot.screentextsaver.service.MessageServiceImplementation;

public final class ScreenTextSaver implements ModInitializer {
    @Override
    public void onInitialize() {
        // Register configs.
        final var config = AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new)
                                     .getConfig();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            final var sessionFactory = buildSessionFactory(config);
            final var messageRepository = new MessageRepositoryImplementation(sessionFactory);
            final var messageService = new MessageServiceImplementation(messageRepository);
            // Register client-to-server payload.
            PayloadTypeRegistry.playC2S().register(
                SendScreenMessagePayload.PACKET_TYPE,
                SendScreenMessagePayload.STREAM_CODEC
            );
            // Register global packet receivers.
            final var payloadHandler = new SendScreenMessagePayloadHandler(messageService);
            ServerPlayNetworking.registerGlobalReceiver(
                SendScreenMessagePayload.PACKET_TYPE,
                payloadHandler
            );
        }
    }

    private static SessionFactory buildSessionFactory(final ModConfig config) {
        final var cfg = new Configuration();
        cfg.addAnnotatedClass(MessageEntity.class);
        cfg.setProperty("hibernate.connection.url", config.databaseUrl);
        cfg.setProperty("hibernate.connection.username", config.databaseUser);
        cfg.setProperty("hibernate.connection.password", config.databasePassword);
        return cfg.buildSessionFactory();
    }
}
