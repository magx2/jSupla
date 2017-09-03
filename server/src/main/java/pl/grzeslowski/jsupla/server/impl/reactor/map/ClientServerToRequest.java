package pl.grzeslowski.jsupla.server.impl.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;

import java.util.function.Function;

public class ClientServerToRequest implements Function<ClientServer, Request> {
    @Override
    public Request apply(final ClientServer clientServer) {
        //        if (clientServer instanceof SuplaChannelNewValue) {
        //
        //        }
        //        if (clientServer instanceof SuplaChannelNewValueB) {
        //
        //        }
        //        if (clientServer instanceof SuplaRegisterClient) {
        //
        //        }
        //        if (clientServer instanceof SuplaRegisterClientB) {
        //
        //        }
        throw new UnsupportedOperationException(); // TODO
    }
}
