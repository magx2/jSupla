package pl.grzeslowski.jsupla.server.impl.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;

import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ToServerProtoToRequest implements Function<ToServerProto, Request> {
    private final ClientServerToRequest clientServerToRequest;
    private final DeviceServerToRequest deviceServerToRequest;
    private final DeviceClientServerToRequest deviceClientServerToRequest;

    public ToServerProtoToRequest(final ClientServerToRequest clientServerToRequest,
                                  final DeviceServerToRequest deviceServerToRequest,
                                  final DeviceClientServerToRequest deviceClientServerToRequest) {
        this.clientServerToRequest = requireNonNull(clientServerToRequest);
        this.deviceServerToRequest = requireNonNull(deviceServerToRequest);
        this.deviceClientServerToRequest = requireNonNull(deviceClientServerToRequest);
    }

    @Override
    public Request apply(final ToServerProto proto) {
        if (proto instanceof ClientServer) {
            return clientServerToRequest.apply((ClientServer) proto);
        }
        if (proto instanceof DeviceServer) {
            return deviceServerToRequest.apply((DeviceServer) proto);
        }
        if (proto instanceof DeviceClientServer) {
            return deviceClientServerToRequest.apply((DeviceClientServer) proto);
        }

        throw new IllegalArgumentException(format("Don't know how to map this class %s to Request!",
                proto.getClass().getSimpleName()));
    }
}
