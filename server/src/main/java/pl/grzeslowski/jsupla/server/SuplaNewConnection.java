package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequest;

public final class SuplaNewConnection {
    private final Publisher<SuplaConnection> publisher;
    private final RegisterDeviceRequest registerDeviceRequest;
    private final SuplaChannel channel;

    public SuplaNewConnection(final Publisher<SuplaConnection> publisher,
                              final RegisterDeviceRequest registerDeviceRequest,
                              final SuplaChannel channel) {
        this.publisher = publisher;
        this.registerDeviceRequest = registerDeviceRequest;
        this.channel = channel;
    }

    public Publisher<SuplaConnection> getPublisher() {
        return publisher;
    }

    public RegisterDeviceRequest getRegisterDeviceRequest() {
        return registerDeviceRequest;
    }

    public SuplaChannel getChannel() {
        return channel;
    }
}
