package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.SuplaChannel;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequest;
import reactor.core.publisher.Flux;

public final class SuplaNewConnection {
    private final Flux<SuplaDataPackageConnection> flux;
    private final RegisterDeviceRequest registerDeviceRequest;
    private final SuplaChannel channel;

    public SuplaNewConnection(final Flux<SuplaDataPackageConnection> flux,
                              final RegisterDeviceRequest registerDeviceRequest,
                              final SuplaChannel channel) {
        this.flux = flux;
        this.registerDeviceRequest = registerDeviceRequest;
        this.channel = channel;
    }

    public Flux<SuplaDataPackageConnection> getFlux() {
        return flux;
    }

    public RegisterDeviceRequest getRegisterDeviceRequest() {
        return registerDeviceRequest;
    }

    public SuplaChannel getChannel() {
        return channel;
    }
}