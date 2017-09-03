package pl.grzeslowski.jsupla.server.api;

import static java.lang.String.format;

public class ServerRunException extends RuntimeException {
    public ServerRunException(Server server, final Exception e) {
        super(format("There was error when starting server!server details: %s", server), e);
    }
}
