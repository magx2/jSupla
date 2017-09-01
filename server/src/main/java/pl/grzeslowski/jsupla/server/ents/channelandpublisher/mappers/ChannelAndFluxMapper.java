package pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndFlux;
import pl.grzeslowski.jsupla.server.ents.channels.Channel;

import java.util.function.Function;

public interface ChannelAndFluxMapper<CaFT1 extends ChannelAndFlux<? extends Channel<?>, ?>,
                                             CaFT2 extends ChannelAndFlux<? extends Channel<?>, ?>>
        extends Function<CaFT1, CaFT2> {
}
