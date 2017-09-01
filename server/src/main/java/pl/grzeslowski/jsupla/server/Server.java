package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageChannel;

public interface Server extends AutoCloseable {
    Publisher<Publisher<SuplaDataPackageChannel>> run() throws Exception;
}
