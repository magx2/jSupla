package pl.grzeslowski.jsupla.protocol.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.ClientServer;

public interface ClientServerDecoder<T extends ClientServer> extends Decoder<T> {
}
