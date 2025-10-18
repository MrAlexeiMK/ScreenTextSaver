package ru.iot.screentextsaver.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iot.screentextsaver.client.network.MessagePacketSender;

/**
 * Simple GUI screen for sending text from the client to the server.
 */
public class SimpleTextSendScreen extends Screen {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTextSendScreen.class);

    private static final String screenTitle = "Отправка текста";
    private static final String editBoxPlaceholder = "Введите текст";
    private static final String sendButtonText = "Отправить";

    private final MessagePacketSender messagePacketSender;

    public SimpleTextSendScreen(final MessagePacketSender messagePacketSender) {
        super(Component.literal(screenTitle));
        this.messagePacketSender = messagePacketSender;
    }

    @Override
    protected void init() {
        super.init();
        logger.info("Initialize simple text send screen.");
        // Create the text input field.
        final var textField = new EditBox(
            this.font,
            this.width / 2 - 100,
            this.height / 2 - 20,
            200, 20,
            Component.literal(editBoxPlaceholder)
        );
        this.addRenderableWidget(textField);
        // Create the send button.
        final var sendButton = Button.builder(Component.literal(sendButtonText), button -> {
            final var message = textField.getValue().trim();
            if (!message.isEmpty()) {
                // Send packet to server.
                messagePacketSender.sendToServer(message);
                textField.setValue("");
            } else {
                logger.info("Empty message provided.");
            }
        }).bounds(this.width / 2 - 50, this.height / 2 + 10, 100, 20).build();
        this.addRenderableWidget(sendButton);
    }

    @Override
    public boolean keyPressed(final int keyCode, final int scanCode, final int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            // "Esc" pressed scenario.
            logger.info("'Esc' pressed, close the screen.");
            onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
