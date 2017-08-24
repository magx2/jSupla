package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequest;

public final class SupplaNewConnection {
    private final Publisher<SuplaConnection> publisher;
    private final RegisterDeviceRequest registerDeviceRequest;
    private final SuplaChannel channel;

    public SupplaNewConnection(final Publisher<SuplaConnection> publisher,
                               final RegisterDeviceRequest registerDeviceRequest,
                               final SuplaChannel channel) {
        this.publisher = publisher;
        this.registerDeviceRequest = registerDeviceRequest;
        this.channel = channel;
    }
}
