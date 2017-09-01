package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageAndChannel;

public interface Server extends AutoCloseable {
    Publisher<Publisher<SuplaDataPackageAndChannel>> run() throws Exception;
}
