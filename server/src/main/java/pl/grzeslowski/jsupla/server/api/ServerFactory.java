package pl.grzeslowski.jsupla.server.api;


import lombok.NonNull;

import java.util.function.Supplier;

public interface ServerFactory {
    /**
     * Creates new server. Server should be already running and should be ready for receiving data packets from
     * Supla devices.
     *
     * @param serverProperties properties for new server
     * @return running server
     * @throws IllegalArgumentException when serverProperties does not have all required fields
     */
    Server createNewServer(@NonNull ServerProperties serverProperties, @NonNull Supplier<MessageHandler> messageHandlerFactory) throws IllegalArgumentException;
}
