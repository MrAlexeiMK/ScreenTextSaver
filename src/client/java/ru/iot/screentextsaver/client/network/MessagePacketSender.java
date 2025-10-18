package ru.iot.screentextsaver.client.network;

/**
 * Abstraction for sending client packet to server.
 */
public interface MessagePacketSender {
    /**
     * Sends text from the client screen to the server.
     * @param screenText The text from the screen to send.
     */
    void sendToServer(String screenText);
}
