package pl.grzeslowski.jsupla.server.impl.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;

import java.util.function.Function;

public class DeviceServerToRequest implements Function<DeviceServer, Request> {
    @Override
    public Request apply(final DeviceServer deviceServer) {
        throw new UnsupportedOperationException(); // TODO
    }
}
