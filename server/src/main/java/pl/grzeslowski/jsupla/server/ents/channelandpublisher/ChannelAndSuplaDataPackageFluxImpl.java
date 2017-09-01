package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;
import reactor.core.publisher.Flux;

public class ChannelAndSuplaDataPackageFluxImpl extends ChannelAndFluxImpl<SuplaDataPacketChannel, SuplaDataPacket>
        implements ChannelAndSuplaDataPackageFlux {
    public ChannelAndSuplaDataPackageFluxImpl(final SuplaDataPacketChannel channel, final Flux<SuplaDataPacket> flux) {
        super(channel, flux);
    }
}
