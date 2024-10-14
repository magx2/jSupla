package pl.grzeslowski.jsupla.server.api;


import lombok.NonNull;
import pl.grzeslowski.jsupla.server.netty.MessageHandlerFactory;

public interface ServerFactory {
    /**
     * Creates new server. Server should be already running and should be ready for receiving data packets from
     * Supla devices.
     *
     * @param serverProperties properties for new server
     * @param messageHandlerFactory factory
     * @return running server
     * @throws IllegalArgumentException when serverProperties does not have all required fields
     */
    Server createNewServer(@NonNull ServerProperties serverProperties, @NonNull MessageHandlerFactory messageHandlerFactory) throws IllegalArgumentException;
}
