package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;

public interface Server extends AutoCloseable {
    Publisher<ChannelAndSuplaDataPackageFlux> run() throws Exception;
}
