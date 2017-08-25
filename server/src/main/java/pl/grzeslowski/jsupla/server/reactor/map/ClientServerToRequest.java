package pl.grzeslowski.jsupla.server.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

import java.util.function.Function;

public class ClientServerToRequest implements Function<ClientServer, Request> {
    @Override
    public Request apply(final ClientServer clientServer) {
        if (clientServer instanceof SuplaChannelNewValue) {

        }
        if (clientServer instanceof SuplaChannelNewValueB) {

        }
        if (clientServer instanceof SuplaRegisterClient) {

        }
        if (clientServer instanceof SuplaRegisterClientB) {

        }
        throw new UnsupportedOperationException(); // TODO
    }
}
