package pl.grzeslowski.jsupla.server.api.exceptions;

import pl.grzeslowski.jsupla.server.api.Server;

import static java.lang.String.format;

public final class CloseServerException extends RuntimeException {
    public CloseServerException(final Server server, final Exception e) {
        super(format("Error while closing server! Server details: %s", server), e);
    }
}
