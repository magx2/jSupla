package pl.grzeslowski.jsupla.protocol.api.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;

public interface ServerClientDecoder<T extends ServerClient> extends Decoder<T> {
}
