package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.ServerClient;

public interface ServerClientEncoder<T extends ServerClient> extends Encoder<T> {
}
