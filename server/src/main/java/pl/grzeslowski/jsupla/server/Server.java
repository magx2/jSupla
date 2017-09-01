package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageConnection;

public interface Server extends AutoCloseable {
    Publisher<Publisher<SuplaDataPackageConnection>> run() throws Exception;
}
