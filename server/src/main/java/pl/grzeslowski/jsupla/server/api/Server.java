package pl.grzeslowski.jsupla.server.api;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;

public interface Server extends AutoCloseable {
    Publisher<ChannelAndSuplaDataPackageFlux> run() throws Exception;
}
