package pl.grzeslowski.jsupla.server.impl.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;

import java.util.function.Function;

public class DeviceClientServerToRequest implements Function<DeviceClientServer, Request> {
    @Override
    public Request apply(final DeviceClientServer deviceClientServer) {
        throw new UnsupportedOperationException(); // TODO
    }
}
