package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.ServerClient;

public interface ServerClientDecoder<T extends ServerClient> extends Decoder<T> {
}
