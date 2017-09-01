package pl.grzeslowski.jsupla.server;

import static java.lang.String.format;

public class CloseServerException extends RuntimeException {
    public CloseServerException(final Server server, final Exception e) {
        super(format("Error while closing server! Server details: %s", server), e);
    }
}
