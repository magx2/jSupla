package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.ClientServer;

public interface ClientServerEncoder<T extends ClientServer> extends Encoder<T> {
}
