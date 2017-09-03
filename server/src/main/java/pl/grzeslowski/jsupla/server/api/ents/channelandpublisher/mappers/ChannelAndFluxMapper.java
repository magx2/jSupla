package pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndFlux;
import pl.grzeslowski.jsupla.server.api.ents.channels.Channel;

import java.util.function.Function;

public interface ChannelAndFluxMapper<CaF1T extends ChannelAndFlux<? extends Channel<?>, ?>,
                                             CaF2T extends ChannelAndFlux<? extends Channel<?>, ?>>
        extends Function<CaF1T, CaF2T> {
}
