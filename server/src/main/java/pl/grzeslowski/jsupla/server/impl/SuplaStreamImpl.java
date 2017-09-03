package pl.grzeslowski.jsupla.server.impl;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.api.CloseServerException;
import pl.grzeslowski.jsupla.server.api.Server;
import pl.grzeslowski.jsupla.server.api.ServerRunException;
import pl.grzeslowski.jsupla.server.api.SuplaStream;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndRequestFlux;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.mappers.FromServerProtoToRequest;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.mappers.SuplaDataPacketToFromServerProto;
import reactor.core.publisher.Flux;

import java.util.concurrent.Callable;

public class SuplaStreamImpl implements SuplaStream {
    private final Callable<? extends Server> serverSupplier;
    private final SuplaDataPacketToFromServerProto suplaDataPacketToFromServerProto;
    private final FromServerProtoToRequest fromServerProtoToRequest;

    public SuplaStreamImpl(final Callable<? extends Server> serverSupplier,
                           final SuplaDataPacketToFromServerProto suplaDataPacketToFromServerProto,
                           final FromServerProtoToRequest fromServerProtoToRequest) {
        this.serverSupplier = serverSupplier;
        this.suplaDataPacketToFromServerProto = suplaDataPacketToFromServerProto;
        this.fromServerProtoToRequest = fromServerProtoToRequest;
    }

    @Override
    public Flux<ChannelAndRequestFlux> fullStream() {
        return Flux.using(serverSupplier, this::runServer, this::closeServer)
                       .map(suplaDataPacketToFromServerProto)
                       .map(fromServerProtoToRequest);
    }

    private Publisher<ChannelAndSuplaDataPackageFlux> runServer(final Server server) {
        try {
            return server.run();
        } catch (Exception e) {
            throw new ServerRunException(server, e);
        }
    }

    private void closeServer(final Server server) {
        try {
            server.close();
        } catch (Exception e) {
            throw new CloseServerException(server, e);
        }
    }
}
