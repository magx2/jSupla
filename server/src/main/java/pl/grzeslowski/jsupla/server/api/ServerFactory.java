package pl.grzeslowski.jsupla.server.api;

import javax.validation.constraints.NotNull;

public interface ServerFactory {
    /**
     * Creates new server. Server should be already running and should be ready for receiving data packets from
     * Supla devices.
     *
     * @throws IllegalArgumentException when serverProperties does not have all required fields
     */
    Server createNewServer(@NotNull ServerProperties serverProperties) throws IllegalArgumentException;
}
